package com.gh.emap.fileA;

import com.ekito.simpleKML.Serializer;
import com.ekito.simpleKML.model.Coordinate;
import com.ekito.simpleKML.model.Coordinates;
import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Feature;
import com.ekito.simpleKML.model.Geometry;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.LineString;
import com.ekito.simpleKML.model.LineStyle;
import com.ekito.simpleKML.model.Placemark;
import com.ekito.simpleKML.model.Style;
import com.ekito.simpleKML.model.StyleSelector;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.overlayA.LineObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2017/1/9.
 * 覆盖物 线 信息文件
 */

public class RWLineFile {
    private static String STYLE_LINE_ID = "style_line_id";
    private static String STYLE_LINE_ID_URL = "#" + STYLE_LINE_ID;

    public static LineObject read(String fileName, String[] errorMsg) {
        LineObject object = null;

        try {
            File file = new File(fileName);
            if(file.exists()) {
                Serializer serializer = new Serializer();
                Kml kml = serializer.read(file);
                Document document = (Document)kml.getFeature();
                Placemark placemark = (Placemark)document.getFeatureList().get(0);
                Style style = (Style)placemark.getStyleSelector().get(0);
                LineStyle lineStyle = style.getLineStyle();
                LineString lineString = (LineString)placemark.getGeometryList().get(0);
                Coordinates coordinates = lineString.getCoordinates();

                object = new LineObject();

                object.getMyGraphicAttribute().setName(placemark.getName());
                object.getMyGraphicAttribute().setType(placemark.getDescription());

                object.getMyLineOption().setStrokeColor(Long.valueOf(lineStyle.getColor(), 16).intValue());
                object.getMyLineOption().setStrokeWidth(lineStyle.getWidth().intValue());

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

    public static ArrayList<LineObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<LineObject> items = new ArrayList<LineObject>();

        for(int i = 0; i < files.size(); i ++) {
            LineObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String fileName, LineObject object, String[] errorMsg) {
        try {
            Kml kml = new Kml();

            Document document = new Document();

            List<Feature> placemarkFeatures = new ArrayList<>();

            Placemark placemark = new Placemark();
            placemark.setName(object.getMyGraphicAttribute().getName());
            placemark.setDescription(object.getMyGraphicAttribute().getType());

            LineStyle lineStyle = new LineStyle();
            lineStyle.setColor(Integer.toHexString(object.getMyLineOption().getStrokeColor()));
            lineStyle.setWidth(Float.valueOf(object.getMyLineOption().getStrokeWidth()));

            List<StyleSelector> styleSelectors = new ArrayList<>();
            Style style = new Style();
            style.setId(STYLE_LINE_ID);
            style.setLineStyle(lineStyle);
            styleSelectors.add(style);

            placemark.setStyleSelector(styleSelectors);

            List<Geometry> lineStrings = new ArrayList<>();

            LineString lineString = new LineString();
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
            lineString.setCoordinates(coordinates);

            lineStrings.add(lineString);
            placemark.setStyleUrl(STYLE_LINE_ID_URL);
            placemark.setGeometryList(lineStrings);
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
