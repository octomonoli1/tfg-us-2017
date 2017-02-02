import Component from 'metal-component/src/Component';
import Soy from 'metal-soy/src/Soy';

import core from 'metal/src/core';
import { CancellablePromise } from 'metal-promise/src/promise/Promise';

import componentTemplates from './RotateComponent.soy';
import controlsTemplates from './RotateControls.soy';

/**
 * Rotate Component
 */
class RotateComponent extends Component {
	/**
	 * @inheritDoc
	 */
	attached() {
		this.cache_ = {};
		this.rotationAngle_ = 0;
	}

	/**
	 * @inheritDoc
	 */
	detached() {
		this.cache_ = {};
	}

	/**
	 * Rotates the image to the current selected rotation angle.
	 *
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the processing is complete.
	 */
	preview(imageData) {
		return this.process(imageData);
	}

	/**
	 * Rotates the image to the current selected rotation angle.
	 *
	 * @param  {ImageData} imageData ImageData representation of the image.
	 * @return {CancellablePromise} A promise that will resolve when the processing is complete.
	 */
	process(imageData) {
		let promise = this.cache_[this.rotationAngle_];

		if (!promise) {
			promise = this.rotate_(imageData, this.rotationAngle_);

			this.cache_[this.rotationAngle_] = promise;
		}

		return promise;
	}

	/**
	 * Rotates the passed ImageData to the current rotation angle.
	 *
	 * @protected
	 * @param  {ImageData} imageData The ImageData to rotate
	 * @param  {number} rotationAngle Normalized rotation angle in degrees in the range [0-360)
	 * @return {CancellablePromise} A promise to be fullfilled when the image has been rotated.
	 */
	rotate_(imageData, rotationAngle) {
		let cancellablePromise = new CancellablePromise((resolve, reject) => {
			let imageWidth = imageData.width;
			let imageHeight = imageData.height;

			let swapDimensions  = (rotationAngle / 90) % 2;

			let imageCanvas = document.createElement('canvas');
			imageCanvas.width = imageWidth;
			imageCanvas.height = imageHeight;
			imageCanvas.getContext('2d').putImageData(imageData, 0, 0);

			let offscreenCanvas = document.createElement('canvas');
			offscreenCanvas.width = swapDimensions ? imageHeight : imageWidth;
			offscreenCanvas.height = swapDimensions ? imageWidth : imageHeight;

			let offscreenContext = offscreenCanvas.getContext('2d');
			offscreenContext.save();
			offscreenContext.translate(offscreenCanvas.width / 2, offscreenCanvas.height / 2);
			offscreenContext.rotate(rotationAngle * Math.PI / 180);
			offscreenContext.drawImage(imageCanvas, -imageCanvas.width / 2, -imageCanvas.height / 2);
			offscreenContext.restore();

			resolve(offscreenContext.getImageData(0, 0, offscreenCanvas.width, offscreenCanvas.height));
		});

		return cancellablePromise;
	}

	/**
	 * Rotates the image 90º counter-clockwise.
	 */
	rotateLeft() {
		this.rotationAngle_ = (this.rotationAngle_ - 90) % 360;
		this.requestImageEditorPreview();
	}

	/**
	 * Rotates the image 90º clockwise.
	 */
	rotateRight() {
		this.rotationAngle_ = (this.rotationAngle_ + 90) % 360;
		this.requestImageEditorPreview();
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
RotateComponent.STATE = {
	/**
	 * Path of this module
	 * @type {Function}
	 */
	modulePath: {
		validator: core.isString
	},

	/**
	 * Injected method to notify the editor this component
	 * wants to generate a preview version of the image.
	 * @type {Function}
	 */
	requestImageEditorPreview: {
		validator: core.isFunction
	}
};

// Register component
Soy.register(RotateComponent, componentTemplates);

export default RotateComponent;