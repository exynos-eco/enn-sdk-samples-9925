// Copyright (c) 2023 Samsung Electronics Co. LTD. Released under the MIT License.

package com.samsung.segmentation.data

object ModelConstants {
    const val MODEL_NAME = "deeplabv3.nnc"

    val INPUT_DATA_TYPE = DataType.FLOAT32
    val INPUT_DATA_LAYER = LayerType.HWC

    const val INPUT_SIZE_W = 257
    const val INPUT_SIZE_H = 257
    const val INPUT_SIZE_C = 3

    const val INPUT_CONVERSION_SCALE = 127.5F
    const val INPUT_CONVERSION_OFFSET = 127.5F

    val OUTPUT_DATA_TYPE = DataType.FLOAT32

    const val OUTPUT_SIZE_H = INPUT_SIZE_H
    const val OUTPUT_SIZE_W = INPUT_SIZE_W
    const val OUTPUT_SIZE_C = 21

    const val OUTPUT_CONVERSION_SCALE = 1F
    const val OUTPUT_CONVERSION_OFFSET = 0F
}
