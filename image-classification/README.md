# Image Classification In Android
The "Image Classification in Android" sample application demonstrates the execution of a converted "[Inception v4](https://tfhub.dev/tensorflow/lite-model/inception_v4_quant/1/default/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Accelerate**" hardware type option.

## Functionality
The application accepts input from either a camera feed or an image file and classifies the object within the input. 
The classified items, their corresponding scores, and the inference time are displayed at the bottom of the application interface.

## Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from this repository.
2. Open the sample application project in Android Studio.
3. Connect the ERD Board to the computer.
4. Run the application (using Shift + F10).
5. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
2. Copy the corresponding label text file to the `assets` directory.
3. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
4. If you model inputs and outputs are different from the pre-designed Sample Application, you will need to change the `preProcess()` and `postProcess()` functions.
