package com.gh.emap.fileA;

import com.ekito.simpleKML.Serializer;
import com.ekito.simpleKML.model.Boundary;
import com.ekito.simpleKML.model.Coordinate;
import com.ekito.simpleKML.model.Coordinates;
import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Feature;
import com.ekito.simpleKML.model.Geometry;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.LinearRing;
import com.ekito.simpleKML.model.Placemark;
import com.ekito.simpleKML.model.PolyStyle;
import com.ekito.simpleKML.model.Polygon;
import com.ekito.simpleKML.model.Style;
import com.ekito.simpleKML.model.StyleSelector;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.overlayA.PlaneObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class RWPlaneFile {
    private static String STYLE_POLY_ID = "style_poly_id";
    private static String STYLE_POLY_ID_URL = "#" + STYLE_POLY_ID;

    public static PlaneObject read(String fileName, String[] errorMsg) {
        PlaneObject object = null;

        try {
            File file = new File(fileName);
            if(file.exists()) {
                Serializer serializer = new Serializer();
                Kml kml = serializer.read(file);
                Document document = (Document)kml.getFeature();
                Placemark placemark = (Placemark)document.getFeatureList().get(0);
                Style style = (Style)placemark.getStyleSelector().get(0);
                PolyStyle polyStyle = style.getPolyStyle();
                Polygon polygon = (Polygon)placemark.getGeometryList().get(0);
                Boundary outerBoundaryIs = polygon.getOuterBoundaryIs();
                LinearRing linearRing = outerBoundaryIs.getLinearRing();
                Coordinates coordinates = linearRing.getCoordinates();

                object = new PlaneObject();

                object.getMyGraphicAttribute().setName(placemark.getName());
                object.getMyGraphicAttribute().setType(placemark.getDescription());

                object.getMyCircleOption().setFillColor(polyStyle.getFill());
                object.getMyPlaneOption().setStrokeColor(Long.valueOf(polyStyle.getColor(), 16).intValue());

                for(int i = 0; i < coordinates.getList().size(); i ++) {
                    Coordinate coordinate = coordinates.getList().get(i);

                    MyCoordinate myCoordinate = new MyCoordinate();
                    myCoordinate.setLongitude(coordinate.getLongitude());
                    myCoordinate.setLatitude(coordinate.getLatitude());
                    myCoordinate.setAltitude(coordinate.getAltitude());

                    object.getMyCoordinates().add(myCoordinate);
                }
            }
        } catch (Exception exception) {
            object = null;
            errorMsg[0] = errorMsg[0] + exception.getMessage();
        }

        return object;
    }

    public static ArrayList<PlaneObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<PlaneObject> items = new ArrayList<PlaneObject>();

        for(int i = 0; i < files.size(); i ++) {
            PlaneObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String fileName, PlaneObject object, String[] errorMsg) {
        try {
            Kml kml = new Kml();

            Document document = new Document();

            List<Feature> placemarkFeatures = new ArrayList<>();

            Placemark placemark = new Placemark();
            placemark.setName(object.getMyGraphicAttribute().getName());
            placemark.setDescription(object.getMyGraphicAttribute().getType());

            PolyStyle polyStyle = new PolyStyle();

            polyStyle.setId(STYLE_POLY_ID);
            polyStyle.setFill(object.getMyPlaneOption().getFillColor());
            polyStyle.setColor(Integer.toHexString(object.getMyPlaneOption().getStrokeColor()));

            List<StyleSelector> styleSelectors = new ArrayList<>();
            Style style = new Style();
            style.setPolyStyle(polyStyle);
            styleSelectors.add(style);

            placemark.setStyleSelector(styleSelectors);

            List<Geometry> polygons = new ArrayList<>();

            Polygon polygon = new Polygon();
            polygon.setTessellate(1);
            Boundary outerBoundaryIs = new Boundary();

            LinearRing linearRing = new LinearRing();
            Coordinates coordinates = null;
            for(int i = 0; i < object.getMyCoordinates().size(); i ++) {
                MyCoordinate myCoordinate = object.getMyCoordinates().get(i);

                Coordinate coordinate = new Coordinate(myCoordinate.getLongitude(), myCoordinate.getLatitude(), myCoordinate.getAltitude());

                if(coordinates == null) {
                    coordinates = new Coordinates(coordinate.toString());
                } else {
                    coordinates.getList().add(coordinate);
                }
            }
            linearRing.setCoordinates(coordinates);

            outerBoundaryIs.setLinearRing(linearRing);
            polygon.setOuterBoundaryIs(outerBoundaryIs);
            polygons.add(polygon);
            placemark.setStyleUrl(STYLE_POLY_ID_URL);
            placemark.setGeometryList(polygons);
            placemarkFeatures.add(placemark);
            document.setFeatureList(placemarkFeatures);
            kml.setFeature(document);

            Serializer serializer = new Serializer();
            serializer.write(kml, new File(fileName));
        } catch (Exception exception) {
            errorMsg[0] = errorMsg[0] + exception.getMessage();
        }
    }
}
