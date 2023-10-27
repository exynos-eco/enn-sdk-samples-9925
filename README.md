## Introduction
|Sample Name|Description|
|-------------|-------|
|[Image Classification In Android](#image-classification-in-android)|Sample Android application to demonstrate execution of `Efficientnet` model with ENN SDK|
|[NNC Model Tester](#nnc-model-tester)|Sample C++ program that demonstrates execution of model with ENN SDK|

## Android (Kotlin) Samples
This section provides an overview of Android (Kotlin) sample applications. 
Each entry details the functionality of the sample application, its location, and instructions for running it.

### Image Classification In Android
The "Image Classification in Android" sample application demonstrates the execution of a converted "[Efficientnet](https://github.com/PINTO0309/PINTO_model_zoo/tree/main/004_efficientnet)" model using the ENN Framework.

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




## Native Samples
This section provides an overview of the native sample program. 
Each entry details the functionality of the sample program, its location, and instructions for running it.

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
1. Build the program by following the instructions in the "[README](nnc-model-tester/README.md)" file.
1. Execute the program by following "[README](nnc-model-tester/README.md)" file.