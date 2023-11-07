## Introduction
|Sample Name|Description|
|-------------|-------|
|[Image Classification In Android](#image-classification-in-android)|Sample Android application to demonstrate execution of `Inception v4` model with ENN SDK|
|[Object Detection In Android](#object-detection-in-android)|Sample Android application to demonstrate execution of `YOLOv5` model with ENN SDK|
|[Segmentation In Android](#segmentation-in-android)|Sample Android application to demonstrate execution of `DeeplabV3` model with ENN SDK|
|[Pose Estimation In Android](#pose-estimation-in-android)|Sample Android application to demonstrate execution of `PoseNet` model with ENN SDK|
|[Image Enhance In Android](#image-enhance-in-android)|Sample Android application to demonstrate execution of `Zero-DCE` model with ENN SDK|
|[Depth Estimation In Andriod](#depth-estimation-in-andriod)|Sample Android application to demonstrate execution of `MiDaS v2` model with ENN SDK|
|[Performance Comparison](#performance-comparison)|Sample Android application to demonstrate the difference between ENN SDK and TFLite|
|[NNC Model Tester](#nnc-model-tester)|Sample C++ program that demonstrates execution of model with ENN SDK|

## Android (Kotlin) Samples
This section provides an overview of Android (Kotlin) sample applications. 
Each entry details the functionality of the sample application, its location, and instructions for running it.

***

### Image Classification In Android
The "Image Classification in Android" sample application demonstrates the execution of a converted "[Inception v4](https://tfhub.dev/tensorflow/lite-model/inception_v4_quant/1/default/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Accelerate**" hardware type option.

#### Functionality
The application accepts input from either a camera feed or an image file and classifies the object within the input. 
The classified items, their corresponding scores, and the inference time are displayed at the bottom of the application interface.

#### Location
The sample is located in the `enn-sdk-samples-9925/image-classification` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Copy the corresponding label text file to the `assets` directory.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Object Detection In Android
The "Object Detection in Android" sample application demonstrates the execution of a converted "[YOLOv5](https://github.com/ultralytics/yolov5)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

#### Functionality
The application accepts input from either a camera feed or an image file and identifies the object within the input. 
A bounding box is drawn around the detected item, and the label and score associated with the object are displayed.
Additionally, the inference time is displayed at the bottom of the application interface.

#### Location
The sample is located in the `enn-sdk-samples-9925/object-detection` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Copy the corresponding label text file to the `assets` directory.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Segmentation In Android
The "Segmentation in Android" sample application demonstrates the execution of a converted "[DeeplabV3](https://tfhub.dev/tensorflow/lite-model/deeplabv3/1/default/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

#### Functionality
The application accepts input from either a camera feed or an image file and performs segmentation on the object within the input.
Each pixel of the segmented object is overlayed with a color corresponding to its label, providing a visual representation of the classification.
Additionally, the inference time is displayed at the bottom of the application interface.

#### Location
The sample is located in the `enn-sdk-samples-9925/segmentation` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Pose Estimation In Android
The "Pose Estimation In Android" sample application demonstrates the execution of a converted "[PoseNet](https://tfhub.dev/tensorflow/tfjs-model/posenet/mobilenet/float/075/1/default/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

#### Functionality
The application accepts input from either a camera feed or an image file and detects the keypoints of a person within the input. 
It overlays the shape of the person, consisting of points and edges, onto the image. 
Additionally, the inference time is displayed at the bottom of the application interface.

#### Location
The sample is located in the `enn-sdk-samples-9925/pose-estimation` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Image Enhance In Android
The "Image Enhance In Android" sample application demonstrates the execution of a converted "[Zero-DCE](https://tfhub.dev/sayannath/lite-model/zero-dce/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

#### Functionality
The application accepts input from an image file and enhaces it.
Specifically, it takes low-light images and improves their quality.
Additionally, the inference time is displayed at the bottom of the application interface.

#### Location
The sample is located in the `enn-sdk-samples-9925/image-enhance` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Provide the image data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Depth Estimation In Andriod
The "Depth Estimation in Android" sample application demonstrates the execution of a converted "[MiDaS V2](https://tfhub.dev/intel/lite-model/midas/v2_1_small/1/lite/1)" model using the ENN Framework.
The model is converting using ENN SDK Service with "**Default**" hardware type option.

#### Functionality
The application accepts input from either a camera feed or an image file and performs segmentation on the object within the input.
A color representing the estimated distance is overlayed on each pixel, providing a visual representation of depth.
Additionally, the inference time is displayed at the bottom of the application interface. 

#### Location
The sample is located in the `enn-sdk-samples-9925/depth-estimation` directory within the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

To modify the model used in the application, follow these steps:
1. Copy the desired model file to the `assets` directory within the project.
1. Modify parameters in the ModelConstants.kt file to reflect the new model's specifications.
1. If the model's inputs and outputs differ from the pre-designed Sample Application, change the `preProcess()` and `postProcess()` functions.

***

### Performance Comparison
The "Performance Comparison" sample application provides a side-by-side analysis of the execution time differences between the ENN SDK and TFLite.

#### Functionality
This application builds upon the "Image Classification in Android" sample application.
In addition to executing the NNC model using the ENN Framework, it also runs the corresponding TFLite model. 
The results of the execution, along with the inference time for both the ENN Framework and TFLite, are displayed at the bottom of the application interface. 

#### Location
This sample is located under the `enn-sdk-samples-9925/perf-compare` directory in the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize this sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Open the sample application project in Android Studio.
1. Connect the ERD Board to the computer.
1. Run the application (using Shift + F10).
1. Select either "Camera" or "Image" mode and provide the data for inference.

***










## Native Samples
This section provides an overview of the native sample program. 
Each entry details the functionality of the sample program, its location, and instructions for running it.

***

### NNC Model Tester
The "NNC Model Test" sample program illustrates the process of executing an NNC model using the ENN Framework.

#### Functionality
This program accepts various parameters to execute an NNC model with input binary data. 
It can perform golden matching and print the inference time, providing a comprehensive view of the model's execution.
For more detailed information, including specific instructions and parameters, refer to the README.md file in the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925). repository.

#### Location
This sample is located under the `enn-sdk-samples-9925/nnc-model-tester` directory in the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.

#### Getting Started
To utilize the sample application, follow these steps:
1. Download or clone the sample application from the [Github](https://github.com/exynos-eco/enn-sdk-samples-9925) repository.
1. Build the program by following the instructions in the "[README](nnc-model-tester/README.md#preparation)" file.
1. Execute the program by following "[README](nnc-model-tester/README.md#usage)" file.
