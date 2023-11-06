### Depth Estimation In Andriod
The "Depth Estimation in Android" sample application demonstrates the execution of a converted "[MiDaS V2](https://tfhub.dev/intel/lite-model/midas/v2_1_small/1/lite/1)" model using the ENN Framework.

#### Functionality
The application accepts input from either a camera feed or an image file and performs segmentation on the object within the input.
A color representing the estimated distance is overlayed on each pixel, providing a visual representation of depth.
Additionally, the inference time is displayed at the bottom of the application interface. 

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from this repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.