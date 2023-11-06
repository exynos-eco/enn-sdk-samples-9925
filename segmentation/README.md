### Segmentation In Android
The "Segmentation in Android" sample application demonstrates the execution of a converted "[DeeplabV3](https://tfhub.dev/tensorflow/lite-model/deeplabv3/1/default/1)" model using the ENN Framework. 

#### Functionality
The application takes input from either a camera feed or an image file and performs segmentation on the object within the input. 
Each pixel of the segmented object is overlayed with a color corresponding to its label, providing a visual representation of the classification.
Additionally, the inference time is presented at the bottom of the application interface. 

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