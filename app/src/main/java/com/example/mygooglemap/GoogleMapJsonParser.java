package com.example.mygooglemap;


import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleMapJsonParser {
    private  String startAddress;
    private String endAddress;
    private LatLng endPoint;
    private Steps[] legsArray;
    private String distance;



    public GoogleMapJsonParser(String jsonRespond){
        Steps[] legs = null;
        try {

            JSONObject respondJsonArray = new JSONObject(jsonRespond);
            JSONObject legsJsonObject     = respondJsonArray
                    .getJSONArray("routes")
                    .getJSONObject(0)
                    .getJSONArray("legs")
                    .getJSONObject(0);
            distance = legsJsonObject.getJSONObject("distance").getString("text");

            startAddress = legsJsonObject.getString("start_address");
            double endLat = legsJsonObject.getJSONObject("end_location").getDouble("lat");
            double endLng = legsJsonObject.getJSONObject("end_location").getDouble("lng");
            endPoint = new LatLng(endLat, endLng);
            endAddress =legsJsonObject.getString("end_address");


            JSONArray stepsJsonArray     = legsJsonObject.getJSONArray("steps");

            legs = new Steps[stepsJsonArray.length()];
            for (int i = 0; i < stepsJsonArray.length(); i++ ){
                JSONObject  oneJsonstep = stepsJsonArray.getJSONObject(i);
                Steps objectStep = new Steps();
                objectStep.setStartLocationLat(oneJsonstep.getJSONObject("start_location").getDouble("lat"));
                objectStep.setStartLocationLng(oneJsonstep.getJSONObject("start_location").getDouble("lng"));
                objectStep.setEndLocationLat(oneJsonstep.getJSONObject("end_location").getDouble("lat"));
                objectStep.setEndLocationLng(oneJsonstep.getJSONObject("end_location").getDouble("lng"));
                legs[i] = objectStep;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
       this.legsArray = legs;
    }

    public Steps[] getLegsArray(){
        return this.legsArray;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public String getDistance() {
        return distance;
    }

    public  String fakeRespond(){
        return "{\n" +
                "   \"routes\" : [\n" +
                "      {\n" +
                "         \"bounds\" : {\n" +
                "            \"northeast\" : {\n" +
                "               \"lat\" : 51.5284261,\n" +
                "               \"lng\" : -0.1014954\n" +
                "            },\n" +
                "            \"southwest\" : {\n" +
                "               \"lat\" : 51.5009238,\n" +
                "               \"lng\" : -0.1263817\n" +
                "            }\n" +
                "         },\n" +
                "         \"copyrights\" : \"Картографические данные © 2014 Google\",\n" +
                "         \"legs\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4,7 км\",\n" +
                "                  \"value\" : 4712\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"10 мин.\",\n" +
                "                  \"value\" : 609\n" +
                "               },\n" +
                "               \"end_address\" : \"Сейнт-Маргарет-стрит, Вестминстер, Лондон SW1P 3JX, Великобритания\",\n" +
                "               \"end_location\" : {\n" +
                "                  \"lat\" : 51.5009238,\n" +
                "                  \"lng\" : -0.1261621\n" +
                "               },\n" +
                "               \"start_address\" : \"258 Спенсер-стрит, Лондон EC1V, Великобритания\",\n" +
                "               \"start_location\" : {\n" +
                "                  \"lat\" : 51.5284261,\n" +
                "                  \"lng\" : -0.1014954\n" +
                "               },\n" +
                "               \"steps\" : [\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,2 км\",\n" +
                "                        \"value\" : 235\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 37\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.52779349999999,\n" +
                "                        \"lng\" : -0.1047403\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Направляйтесь на \\u003cb\\u003eзапад\\u003c/b\\u003e по \\u003cb\\u003eSpencer St\\u003c/b\\u003e в сторону \\u003cb\\u003eEarlstoke St\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"ucoyHjyRV|BV~B@PNlAV~BHz@TxADR\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5284261,\n" +
                "                        \"lng\" : -0.1014954\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,2 км\",\n" +
                "                        \"value\" : 230\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 36\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.52583389999999,\n" +
                "                        \"lng\" : -0.1040132\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eналево\\u003c/b\\u003e на \\u003cb\\u003eSt John St/B501\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"u_oyHrmSLCr@WZQx@k@f@[JKVMJGLGFCHCJ?F?F?zAT\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.52779349999999,\n" +
                "                        \"lng\" : -0.1047403\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,1 км\",\n" +
                "                        \"value\" : 95\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 16\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.52561129999999,\n" +
                "                        \"lng\" : -0.1053382\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eнаправо\\u003c/b\\u003e на \\u003cb\\u003eSkinner St/B502\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"msnyH`iS@ZB`@DXLzAL~@BT\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.52583389999999,\n" +
                "                        \"lng\" : -0.1040132\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,1 км\",\n" +
                "                        \"value\" : 129\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 20\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5249161,\n" +
                "                        \"lng\" : -0.1067761\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Плавный поворот \\u003cb\\u003eналево\\u003c/b\\u003e на \\u003cb\\u003eCorporation Row\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-slight-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"arnyHjqSB?@@BB@D@FHb@@PxAvDDHBFDDDD\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.52561129999999,\n" +
                "                        \"lng\" : -0.1053382\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,2 км\",\n" +
                "                        \"value\" : 193\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 31\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5238294,\n" +
                "                        \"lng\" : -0.1089324\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Продолжайте движение по \\u003cb\\u003eBowling Green Ln\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"wmnyHjzSNRpAlDr@xBLVt@xA\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5249161,\n" +
                "                        \"lng\" : -0.1067761\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"1,5 км\",\n" +
                "                        \"value\" : 1477\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"3 мин.\",\n" +
                "                        \"value\" : 177\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5112026,\n" +
                "                        \"lng\" : -0.1042919\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eналево\\u003c/b\\u003e на \\u003cb\\u003eFarringdon Rd/A201\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eПродолжайте движение по A201\\u003c/div\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"}fnyHxgTb@i@DGj@{@j@s@X_@b@q@`@g@^c@VYPQ`@_@fBoAx@i@d@Sn@Uf@Qf@Oj@Od@Kb@KNCLE`@KdAWdAUXGHALCH?L?RAHAH?j@IdAOTEPCh@K\\\\E^GHAFAh@G\\\\Ef@Gj@Ij@G\\\\Ev@Mr@K`@CFAJM@A@A`AOJC|AOb@IL@LFHAFAD?TANALAL?N?L?H?N?J?NAT?HGD?DAf@AHAXCDADAFCFCN?LAH?`@AX?RD\\\\JHBRJP@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5238294,\n" +
                "                        \"lng\" : -0.1089324\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"1,7 км\",\n" +
                "                        \"value\" : 1686\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"3 мин.\",\n" +
                "                        \"value\" : 194\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.50464890000001,\n" +
                "                        \"lng\" : -0.1231268\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eнаправо\\u003c/b\\u003e на \\u003cb\\u003eVictoria Embankment\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"_xkyHxjSZl@JXBD?@FPDL@R@B?J@`@?X?v@Aj@?j@@t@@t@AfA?PAPAPAPCNCN@pA?L@d@A|DCrGAhB?`B?hBRpF@ZHrAZhDLdB^rCBNHz@RtAh@nCTlAHZBDHZ\\\\tAHRRp@Tl@z@dBx@~Ar@fABFx@~@tAjAHF`Ap@r@f@@?DDVTTPjAdADBNJvAt@vAf@rDx@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5112026,\n" +
                "                        \"lng\" : -0.1042919\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,2 км\",\n" +
                "                        \"value\" : 231\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 45\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5048192,\n" +
                "                        \"lng\" : -0.1263817\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eнаправо\\u003c/b\\u003e на \\u003cb\\u003eHorse Guards Ave\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"aojyHp`WCXQdBa@vEAR?LAH?H?H?J?L?L@J?N@J@L@N@PHfADh@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.50464890000001,\n" +
                "                        \"lng\" : -0.1231268\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0,4 км\",\n" +
                "                        \"value\" : 360\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 36\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5015998,\n" +
                "                        \"lng\" : -0.1261245\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Поверните \\u003cb\\u003eналево\\u003c/b\\u003e на \\u003cb\\u003eWhitehall/A3212\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eПродолжайте движение по A3212\\u003c/div\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"cpjyHztWVG^IXGXGXCn@I`AIVCTAR?PCb@CD?PAPAN?\\\\@X?V@H?XFT@nAH\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5048192,\n" +
                "                        \"lng\" : -0.1263817\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"76 м\",\n" +
                "                        \"value\" : 76\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 мин.\",\n" +
                "                        \"value\" : 17\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.5009238,\n" +
                "                        \"lng\" : -0.1261621\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Продолжайте движение по \\u003cb\\u003eParliament St/A3212\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eПродолжайте движение по A3212\\u003c/div\\u003e\",\n" +
                "                     \"maneuver\" : \"straight\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"_|iyHfsWTDTB`@BR?H?FAFAHA@?\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.5015998,\n" +
                "                        \"lng\" : -0.1261245\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"via_waypoint\" : []\n" +
                "            }\n" +
                "         ],\n" +
                "         \"overview_polyline\" : {\n" +
                "            \"points\" : \"ucoyHjyR`A|I`@zDZlB`A[tA}@r@g@b@UTKTCN?zAT@ZHz@ZzCBTB?DDNbA~A`EHLTXpAlDr@xBbApBh@q@vAoB|@qAjBwB`@_@fBoAx@i@d@SvAg@rA_@fBa@fEaAbCSvCe@nAQzC_@hAMjBYh@ELObAQhBSb@IL@LFHALAd@Cx@At@AT?HGJApAILEVCrACp@P\\\\NP@Zl@N^FRF`@BjA?dE?|BAb@KbA@bIE|J?jETlGd@|FLdB^rCLjARtAh@nC^hBt@jCh@~AtBdEv@nAx@~@tAjAjAx@t@f@~BrBTNvAt@vAf@rDx@CXs@|HCt@DfBRrCjBa@bD[~AIx@CxABn@HdBNv@F\\\\?ZE\"\n" +
                "         },\n" +
                "         \"summary\" : \"A201 и Victoria Embankment\",\n" +
                "         \"warnings\" : [],\n" +
                "         \"waypoint_order\" : []\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }
}

