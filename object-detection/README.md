# Object Detection In Android
The "Object Detection in Android" sample application demonstrates the execution of a converted "[YOLOv5](https://github.com/ultralytics/yolov5)" model using the ENN Framework.
The model is converted using ENN SDK Service with the "**Default**" hardware type option.

## Functionality
The application accepts input from a camera feed or an image file and identifies the object within the input. 
A bounding box is drawn around the detected item, and the label and score associated with the object are displayed.
Additionally, the inference time is displayed at the bottom of the application interface.

## Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from this repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Copy the corresponding label text file to the `assets` directory.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.