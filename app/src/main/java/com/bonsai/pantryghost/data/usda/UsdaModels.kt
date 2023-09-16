package com.bonsai.pantryghost.data.usda

import com.google.gson.annotations.SerializedName

data class UsdaSearchResponse (
    // @SerializedName("foodSearchCriteria" ) var foodSearchCriteria : FoodSearchCriteria? = FoodSearchCriteria(),
    // @SerializedName("currentPage"        ) var currentPage        : Int?                = null,
    // @SerializedName("totalPages"         ) var totalPages         : Int?                = null,
    @SerializedName("totalHits"          ) var totalHits          : Int?                = null,
    @SerializedName("foods"              ) var foods              : ArrayList<UsdaFood> = arrayListOf()
)

data class UsdaFood (
    @SerializedName("fdcId"                  ) var fdcId                  : Int?             = null,
    @SerializedName("dataType"               ) var dataType               : String?          = null,
    @SerializedName("description"            ) var description            : String?          = null,
    @SerializedName("foodCode"               ) var foodCode               : String?          = null,
    @SerializedName("foodNutrients"          ) var foodNutrients          : ArrayList<UsdaNutrient> = arrayListOf(),
    @SerializedName("publicationDate"        ) var publicationDate        : String?          = null,
    @SerializedName("scientificName"         ) var scientificName         : String?          = null,
    @SerializedName("brandOwner"             ) var brandOwner             : String?          = null,
    @SerializedName("gtinUpc"                ) var gtinUpc                : String?          = null,
    @SerializedName("ingredients"            ) var ingredients            : String?          = null,
    @SerializedName("ndbNumber"              ) var ndbNumber              : Int?             = null,
    @SerializedName("additionalDescriptions" ) var additionalDescriptions : String?          = null,
    @SerializedName("allHighlightFields"     ) var allHighlightFields     : String?          = null,
    @SerializedName("score"                  ) var score                  : Float?           = null,
    @SerializedName("servingSize"            ) var servingSize            : Float?             = null,
    @SerializedName("servingSizeUnit"        ) var servingSizeUnit        : String?          = null,
)

data class FoodSearchCriteria (
    @SerializedName("query"        ) var query        : String?           = null,
    @SerializedName("dataType"     ) var dataType     : ArrayList<String> = arrayListOf(),
    @SerializedName("pageSize"     ) var pageSize     : Int?              = null,
    @SerializedName("pageNumber"   ) var pageNumber   : Int?              = null,
    @SerializedName("sortBy"       ) var sortBy       : String?           = null,
    @SerializedName("sortOrder"    ) var sortOrder    : String?           = null,
    @SerializedName("brandOwner"   ) var brandOwner   : String?           = null,
    @SerializedName("tradeChannel" ) var tradeChannel : ArrayList<String> = arrayListOf(),
    @SerializedName("startDate"    ) var startDate    : String?           = null,
    @SerializedName("endDate"      ) var endDate      : String?           = null
)

data class UsdaNutrient (
    @SerializedName("number"                ) var number                : Int?    = null,
    @SerializedName("name"                  ) var name                  : String? = null,
    @SerializedName("amount"                ) var amount                : Double? = null,
    @SerializedName("unitName"              ) var unitName              : String? = null,
    @SerializedName("derivationCode"        ) var derivationCode        : String? = null,
    @SerializedName("derivationDescription" ) var derivationDescription : String? = null,
    @SerializedName("nutrientName"          ) var nutrientName          : String? = null,
    @SerializedName("value"                 ) var value                 : Double? = null,
)

// nutrientName