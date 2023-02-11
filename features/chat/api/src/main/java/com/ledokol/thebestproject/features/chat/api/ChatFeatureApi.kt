package com.ledokol.thebestproject.features.chat.api

import com.ledokol.thebestproject.core.feature.api.FeatureApi

interface ChatFeatureApi: FeatureApi {

        fun chatsRoute(): String

        fun chatRoute(): String

}