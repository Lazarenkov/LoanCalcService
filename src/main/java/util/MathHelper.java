package util;

public class MathHelper {

    public static double roundCeil(double value){
        return Math.ceil(value * 100) / 100;
    }

    public static double roundFloor(double value){
        return Math.floor(value * 100) / 100;
    }
}
