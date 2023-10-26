## Introduction
|Sample Name|Description|
|-------------|-------|
|[Image Classification In Android](#image-classification-in-android)|Sample Android application to demonstrate execution of `Efficientnet` model with ENN SDK|

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