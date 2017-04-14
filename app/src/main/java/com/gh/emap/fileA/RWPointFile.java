package com.gh.emap.fileA;

import com.ekito.simpleKML.Serializer;
import com.ekito.simpleKML.model.Coordinate;
import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Feature;
import com.ekito.simpleKML.model.Geometry;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.Placemark;
import com.ekito.simpleKML.model.Point;
import com.gh.emap.overlayA.PointObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2016/12/14.
 * 覆盖物 点 信息文件
 */

public class RWPointFile {
    private static String placemarkNameSeparator = ",";

    public static PointObject read(String fileName, String[] errorMsg) {
        PointObject object = null;

        try {
            File file = new File(fileName);
            if(file.exists()) {
                Serializer serializer = new Serializer();
                Kml kml = serializer.read(file);
                Document document = (Document)kml.getFeature();
                Placemark placemark = (Placemark)document.getFeatureList().get(0);
                Point point = (Point)placemark.getGeometryList().get(0);
                Coordinate coordinate = point.getCoordinates();

                object = new PointObject();
                object.getMyGraphicAttribute().setName(placemark.getName());
                object.getMyGraphicAttribute().setType(placemark.getDescription());
                object.getMyCoordinate().setLongitude(coordinate.getLongitude());
                object.getMyCoordinate().setLatitude(coordinate.getLatitude());
                object.getMyCoordinate().setAltitude(coordinate.getAltitude());
            }
        } catch (Exception exception) {
            object = null;
            errorMsg[0] = errorMsg[0] + exception.getMessage();
        }

        return object;
    }

    public static ArrayList<PointObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<PointObject> items = new ArrayList<PointObject>();

        for(int i = 0; i < files.size(); i ++) {
            PointObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String fileName, PointObject object, String[] errorMsg) {
        try {
            Kml kml = new Kml();

            Document document = new Document();

            List<Feature> placemarkFeatures = new ArrayList<>();

            Placemark placemark = new Placemark();
            placemark.setName(object.getMyGraphicAttribute().getName());
            placemark.setDescription(object.getMyGraphicAttribute().getType());

            List<Geometry> points = new ArrayList<>();

            Point point = new Point();
            point.setCoordinates(new Coordinate(object.getMyCoordinate().getLongitude(), object.getMyCoordinate().getLatitude(), object.getMyCoordinate().getAltitude()));

            points.add(point);
            placemark.setGeometryList(points);
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
