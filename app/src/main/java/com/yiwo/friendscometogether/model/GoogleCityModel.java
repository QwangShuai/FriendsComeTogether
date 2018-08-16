package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

public class GoogleCityModel {

    /**
     * results : [{"address_components":[{"long_name":"55","short_name":"55","types":["street_number"]},{"long_name":"车站街","short_name":"车站街","types":["route"]},{"long_name":"道里区","short_name":"道里区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"哈尔滨市","short_name":"哈尔滨市","types":["locality","political"]},{"long_name":"黑龙江省","short_name":"黑龙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国黑龙江省哈尔滨市道里区车站街55号","geometry":{"location":{"lat":45.732497,"lng":126.605685},"location_type":"ROOFTOP","viewport":{"northeast":{"lat":45.73384598029149,"lng":126.6070339802915},"southwest":{"lat":45.73114801970849,"lng":126.6043360197085}}},"place_id":"ChIJ14x6nF1_RF4RAn24RyZyA24","types":["street_address"]},{"address_components":[{"long_name":"道里区","short_name":"道里区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"哈尔滨市","short_name":"哈尔滨市","types":["locality","political"]},{"long_name":"黑龙江省","short_name":"黑龙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国黑龙江省哈尔滨市道里区","geometry":{"bounds":{"northeast":{"lat":45.7890533,"lng":126.6351388},"southwest":{"lat":45.54953940000001,"lng":126.1501856}},"location":{"lat":45.75577,"lng":126.616972},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":45.7890533,"lng":126.6351388},"southwest":{"lat":45.54953940000001,"lng":126.1501856}}},"place_id":"ChIJeSgOvuWIQ14RhsdADGVMZ_c","types":["political","sublocality","sublocality_level_1"]},{"address_components":[{"long_name":"哈尔滨市","short_name":"哈尔滨市","types":["locality","political"]},{"long_name":"黑龙江省","short_name":"黑龙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国黑龙江省哈尔滨市","geometry":{"bounds":{"northeast":{"lat":46.6743861,"lng":130.2408395},"southwest":{"lat":44.0622366,"lng":125.6878943}},"location":{"lat":45.80377499999999,"lng":126.534967},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":45.8838096,"lng":126.8799341},"southwest":{"lat":45.6306503,"lng":126.4241087}}},"place_id":"ChIJYRRkpvhkQ14R1SygWnOSfF4","types":["locality","political"]},{"address_components":[{"long_name":"黑龙江省","short_name":"黑龙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国黑龙江省","geometry":{"bounds":{"northeast":{"lat":53.5636239,"lng":135.0956698},"southwest":{"lat":43.4257983,"lng":121.1817528}},"location":{"lat":47.12164720000001,"lng":128.738231},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":53.5636239,"lng":135.0956698},"southwest":{"lat":43.4257983,"lng":121.1817528}}},"place_id":"ChIJf5YvzrYfW14RAK9gg8wDQ_0","types":["administrative_area_level_1","political"]},{"address_components":[{"long_name":"中国","short_name":"CN","types":["country","political"]}],"formatted_address":"中国","geometry":{"bounds":{"northeast":{"lat":53.5609739,"lng":134.7754563},"southwest":{"lat":17.9996,"lng":73.4994136}},"location":{"lat":35.86166,"lng":104.195397},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":53.5609739,"lng":134.7754563},"southwest":{"lat":17.9996,"lng":73.4994136}}},"place_id":"ChIJwULG5WSOUDERbzafNHyqHZU","types":["country","political"]}]
     * status : OK
     */

    private String status;
    private List<ResultsBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * address_components : [{"long_name":"55","short_name":"55","types":["street_number"]},{"long_name":"车站街","short_name":"车站街","types":["route"]},{"long_name":"道里区","short_name":"道里区","types":["political","sublocality","sublocality_level_1"]},{"long_name":"哈尔滨市","short_name":"哈尔滨市","types":["locality","political"]},{"long_name":"黑龙江省","short_name":"黑龙江省","types":["administrative_area_level_1","political"]},{"long_name":"中国","short_name":"CN","types":["country","political"]}]
         * formatted_address : 中国黑龙江省哈尔滨市道里区车站街55号
         * geometry : {"location":{"lat":45.732497,"lng":126.605685},"location_type":"ROOFTOP","viewport":{"northeast":{"lat":45.73384598029149,"lng":126.6070339802915},"southwest":{"lat":45.73114801970849,"lng":126.6043360197085}}}
         * place_id : ChIJ14x6nF1_RF4RAn24RyZyA24
         * types : ["street_address"]
         */

        private String formatted_address;
        private GeometryBean geometry;
        private String place_id;
        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * location : {"lat":45.732497,"lng":126.605685}
             * location_type : ROOFTOP
             * viewport : {"northeast":{"lat":45.73384598029149,"lng":126.6070339802915},"southwest":{"lat":45.73114801970849,"lng":126.6043360197085}}
             */

            private LocationBean location;
            private String location_type;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                /**
                 * lat : 45.732497
                 * lng : 126.605685
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":45.73384598029149,"lng":126.6070339802915}
                 * southwest : {"lat":45.73114801970849,"lng":126.6043360197085}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 45.73384598029149
                     * lng : 126.6070339802915
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 45.73114801970849
                     * lng : 126.6043360197085
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : 55
             * short_name : 55
             * types : ["street_number"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}
