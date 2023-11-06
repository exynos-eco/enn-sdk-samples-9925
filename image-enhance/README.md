# Image Enhance In Android
The "Image Enhance In Android" sample application demonstrates the execution of a converted "[Zero-DCE](https://tfhub.dev/sayannath/lite-model/zero-dce/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

## Functionality
The application accepts input from an image file and enhaces it.
Specifically, it takes low-light images and improves their quality.
Additionally, the inference time is displayed at the bottom of the application interface.

## Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from this repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Provide the image data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.
