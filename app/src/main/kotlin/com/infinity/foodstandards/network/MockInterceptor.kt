package com.infinity.foodstandards.network

import com.infinity.foodstandards.BuildConfig
import com.infinity.foodstandards.model.LocalAuthority
import okhttp3.*

/**
 * This will help us to test our networking code while a particular API is not implemented
 * yet on Backend side.
 */
class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()

            val responseString = when {
                uri.endsWith("authorities") -> getLocalAuthoritiesJson
                uri.endsWith("Establishments?localAuthorityId=48") -> getEstablishmentJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes with DEBUG mode")
        }
    }

}


const val getLocalAuthoritiesJson = "" +
        "{\n" +
        "    \"authorities\": [\n" +
        "        {\n" +
        "            \"LocalAuthorityId\": 197,\n" +
        "            \"LocalAuthorityIdCode\": \"760\",\n" +
        "            \"Name\": \"Aberdeen City\",\n" +
        "            \"FriendlyName\": \"aberdeen-city\",\n" +
        "            \"Url\": \"http://www.aberdeencity.gov.uk\",\n" +
        "            \"SchemeUrl\": \"\",\n" +
        "            \"Email\": \"commercial@aberdeencity.gov.uk\",\n" +
        "            \"RegionName\": \"Scotland\",\n" +
        "            \"FileName\": \"http://ratings.food.gov.uk/OpenDataFiles/FHRS760en-GB.xml\",\n" +
        "            \"FileNameWelsh\": null,\n" +
        "            \"EstablishmentCount\": 1769,\n" +
        "            \"CreationDate\": \"2010-08-17T15:30:24.87\",\n" +
        "            \"LastPublishedDate\": \"2019-09-05T00:32:18.293\",\n" +
        "            \"SchemeType\": 2,\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/authorities/197\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"LocalAuthorityId\": 48,\n" +
        "            \"LocalAuthorityIdCode\": \"062\",\n" +
        "            \"Name\": \"Amber Valley\",\n" +
        "            \"FriendlyName\": \"amber-valley\",\n" +
        "            \"Url\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"SchemeUrl\": \"\",\n" +
        "            \"Email\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"RegionName\": \"East Midlands\",\n" +
        "            \"FileName\": \"http://ratings.food.gov.uk/OpenDataFiles/FHRS062en-GB.xml\",\n" +
        "            \"FileNameWelsh\": null,\n" +
        "            \"EstablishmentCount\": 1015,\n" +
        "            \"CreationDate\": \"2010-08-17T15:30:24.87\",\n" +
        "            \"LastPublishedDate\": \"2019-09-03T00:30:42.147\",\n" +
        "            \"SchemeType\": 1,\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/authorities/48\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }" +
        "   ]" +
        "}"

const val getEstablishmentJson = "{\n" +
        "    \"establishments\": [\n" +
        "        {\n" +
        "            \"FHRSID\": 721741,\n" +
        "            \"LocalAuthorityBusinessID\": \"0000069/FOOD\",\n" +
        "            \"BusinessName\": \"1502 Chocolates\",\n" +
        "            \"BusinessType\": \"Other catering premises\",\n" +
        "            \"BusinessTypeID\": 7841,\n" +
        "            \"AddressLine1\": \"\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE6 \",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"5\",\n" +
        "            \"RatingKey\": \"fhrs_5_en-gb\",\n" +
        "            \"RatingDate\": \"2019-01-25T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": 0,\n" +
        "                \"Structural\": 0,\n" +
        "                \"ConfidenceInManagement\": 0\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": null,\n" +
        "                \"latitude\": null\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/721741\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 1089782,\n" +
        "            \"LocalAuthorityBusinessID\": \"0000186/FH\",\n" +
        "            \"BusinessName\": \"47 Degrees Coffee Pop Up Coffee Bar\",\n" +
        "            \"BusinessType\": \"Mobile caterer\",\n" +
        "            \"BusinessTypeID\": 7846,\n" +
        "            \"AddressLine1\": \"Unit 24 Bailey Brook Industrial Estate Amber Drive Langley Mill Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"NG16 4BE\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"5\",\n" +
        "            \"RatingKey\": \"fhrs_5_en-gb\",\n" +
        "            \"RatingDate\": \"2018-11-23T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": 5,\n" +
        "                \"Structural\": 0,\n" +
        "                \"ConfidenceInManagement\": 0\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.33471596240997\",\n" +
        "                \"latitude\": \"53.016242980957\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/1089782\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 17759,\n" +
        "            \"LocalAuthorityBusinessID\": \"06/00028/FH\",\n" +
        "            \"BusinessName\": \"A Pizza Me\",\n" +
        "            \"BusinessType\": \"Takeaway/sandwich shop\",\n" +
        "            \"BusinessTypeID\": 7844,\n" +
        "            \"AddressLine1\": \"6 - 8 Nottingham Road Ripley Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE5 3DJ\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"4\",\n" +
        "            \"RatingKey\": \"fhrs_4_en-gb\",\n" +
        "            \"RatingDate\": \"2017-06-22T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": null,\n" +
        "                \"Structural\": null,\n" +
        "                \"ConfidenceInManagement\": null\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.403734\",\n" +
        "                \"latitude\": \"53.05067\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/17759\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 17759,\n" +
        "            \"LocalAuthorityBusinessID\": \"06/00028/FH\",\n" +
        "            \"BusinessName\": \"A Pizza Me\",\n" +
        "            \"BusinessType\": \"Takeaway/sandwich shop\",\n" +
        "            \"BusinessTypeID\": 7844,\n" +
        "            \"AddressLine1\": \"6 - 8 Nottingham Road Ripley Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE5 3DJ\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"4\",\n" +
        "            \"RatingKey\": \"fhrs_3_en-gb\",\n" +
        "            \"RatingDate\": \"2017-06-22T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": null,\n" +
        "                \"Structural\": null,\n" +
        "                \"ConfidenceInManagement\": null\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.403734\",\n" +
        "                \"latitude\": \"53.05067\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/17759\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 33726,\n" +
        "            \"LocalAuthorityBusinessID\": \"10/00062/FH\",\n" +
        "            \"BusinessName\": \"Amber Valley Fitness Centre\",\n" +
        "            \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n" +
        "            \"BusinessTypeID\": 1,\n" +
        "            \"AddressLine1\": \"Unit 1 First Floor Rear 157 Nottingham Road Somercotes Alfreton Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE55 4HQ\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"Exempt\",\n" +
        "            \"RatingKey\": \"fhrs_exempt_en-gb\",\n" +
        "            \"RatingDate\": \"1901-01-01T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": null,\n" +
        "                \"Structural\": null,\n" +
        "                \"ConfidenceInManagement\": null\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.374271\",\n" +
        "                \"latitude\": \"53.085649\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/33726\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 25029,\n" +
        "            \"LocalAuthorityBusinessID\": \"08/00037/FH\",\n" +
        "            \"BusinessName\": \"Buzz Stop\",\n" +
        "            \"BusinessType\": \"Mobile caterer\",\n" +
        "            \"BusinessTypeID\": 7846,\n" +
        "            \"AddressLine1\": \"Mobile Unit Clover Nook Road Somercotes Alfreton Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE55 4RF\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"1\",\n" +
        "            \"RatingKey\": \"fhrs_1_en-gb\",\n" +
        "            \"RatingDate\": \"2019-04-16T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": 15,\n" +
        "                \"Structural\": 15,\n" +
        "                \"ConfidenceInManagement\": 20\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.358629\",\n" +
        "                \"latitude\": \"53.090384\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/25029\"\n" +
        "                }\n" +
        "            ]\n" +
        "        },\n" +
        "        {\n" +
        "            \"FHRSID\": 1182,\n" +
        "            \"LocalAuthorityBusinessID\": \"0009531/FH\",\n" +
        "            \"BusinessName\": \"Central England Co-operative\",\n" +
        "            \"BusinessType\": \"Retailers - supermarkets/hypermarkets\",\n" +
        "            \"BusinessTypeID\": 7840,\n" +
        "            \"AddressLine1\": \"1 Derby Road Ripley Derbyshire\",\n" +
        "            \"AddressLine2\": \"\",\n" +
        "            \"AddressLine3\": \"\",\n" +
        "            \"AddressLine4\": \"\",\n" +
        "            \"PostCode\": \"DE5 3EA\",\n" +
        "            \"Phone\": \"\",\n" +
        "            \"RatingValue\": \"2\",\n" +
        "            \"RatingKey\": \"fhrs_2_en-gb\",\n" +
        "            \"RatingDate\": \"2019-05-17T00:00:00\",\n" +
        "            \"LocalAuthorityCode\": \"062\",\n" +
        "            \"LocalAuthorityName\": \"Amber Valley\",\n" +
        "            \"LocalAuthorityWebSite\": \"http://www.ambervalley.gov.uk\",\n" +
        "            \"LocalAuthorityEmailAddress\": \"envhealth@ambervalley.gov.uk\",\n" +
        "            \"scores\": {\n" +
        "                \"Hygiene\": 10,\n" +
        "                \"Structural\": 15,\n" +
        "                \"ConfidenceInManagement\": 10\n" +
        "            },\n" +
        "            \"SchemeType\": \"FHRS\",\n" +
        "            \"geocode\": {\n" +
        "                \"longitude\": \"-1.406107\",\n" +
        "                \"latitude\": \"53.048417\"\n" +
        "            },\n" +
        "            \"RightToReply\": \"\",\n" +
        "            \"Distance\": null,\n" +
        "            \"NewRatingPending\": false,\n" +
        "            \"meta\": {\n" +
        "                \"dataSource\": null,\n" +
        "                \"extractDate\": \"0001-01-01T00:00:00\",\n" +
        "                \"itemCount\": 0,\n" +
        "                \"returncode\": null,\n" +
        "                \"totalCount\": 0,\n" +
        "                \"totalPages\": 0,\n" +
        "                \"pageSize\": 0,\n" +
        "                \"pageNumber\": 0\n" +
        "            },\n" +
        "            \"links\": [\n" +
        "                {\n" +
        "                    \"rel\": \"self\",\n" +
        "                    \"href\": \"http://api.ratings.food.gov.uk/establishments/1182\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }" +
        "      ]" +
        "}"


