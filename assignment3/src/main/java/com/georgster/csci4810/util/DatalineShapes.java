package com.georgster.csci4810.util;

public class DatalineShapes {

    private DatalineShapes() {
        throw new IllegalStateException("Utility class");
    }

    public static Dataline[] getCube() {
        return new Dataline[] {
            new Dataline(-1, 1, -1, 1, 1, -1, "AB"), // AB
            new Dataline(1, 1, -1, 1, -1, -1, "BC"), // BC
            new Dataline(1, -1, -1, -1, -1, -1, "CD"), // CD
            new Dataline(-1, -1, -1, -1, 1, -1, "DA"), // DA
            new Dataline(-1, 1, 1, 1, 1, 1, "EF"), // EF
            new Dataline(1, 1, 1, 1, -1, 1, "FG"), // FG
            new Dataline(1, -1, 1, -1, -1, 1, "GH"), // GH
            new Dataline(-1, -1, 1, -1, 1, 1, "HE"), // HE
            new Dataline(-1, 1, -1, -1, 1, 1, "AE"), // AE
            new Dataline(1, 1, -1, 1, 1, 1, "BF"), // BF
            new Dataline(1, -1, -1, 1, -1, 1, "CG"), // CG
            new Dataline(-1, -1, -1, -1, -1, 1, "DH") // DH
        };
    }

    public static Dataline[] getTriangularPrism() {
        return new Dataline[] {
            new Dataline(-1, 1, -1, 1, 1, -1, "AB"), // base AB
            new Dataline(1, 1, -1, 0, 2, -1, "BC"), // base BC
            new Dataline(0, 2, -1, -1, 1, -1, "CA"), // base CA
            new Dataline(-1, 1, -1, -1, 1, 1, "AD"), // vertical AD
            new Dataline(1, 1, -1, 1, 1, 1, "BE"), // vertical BE
            new Dataline(0, 2, -1, 0, 2, 1, "CF"), // vertical CF
            new Dataline(-1, 1, 1, 1, 1, 1, "DE"), // top DE
            new Dataline(1, 1, 1, 0, 2, 1, "EF"), // top EF
            new Dataline(0, 2, 1, -1, 1, 1, "FD") // top FD
        };
    }

    public static Dataline[] getPentagonalPyramid() {
        double r = 1;
        double h = 1.5;
        return new Dataline[] {
            // Base
            new Dataline(r*Math.cos(0), r*Math.sin(0), 0, r*Math.cos(2*Math.PI/5), r*Math.sin(2*Math.PI/5), 0, "AB"),
            new Dataline(r*Math.cos(2*Math.PI/5), r*Math.sin(2*Math.PI/5), 0, r*Math.cos(4*Math.PI/5), r*Math.sin(4*Math.PI/5), 0, "BC"),
            new Dataline(r*Math.cos(4*Math.PI/5), r*Math.sin(4*Math.PI/5), 0, r*Math.cos(6*Math.PI/5), r*Math.sin(6*Math.PI/5), 0, "CD"),
            new Dataline(r*Math.cos(6*Math.PI/5), r*Math.sin(6*Math.PI/5), 0, r*Math.cos(8*Math.PI/5), r*Math.sin(8*Math.PI/5), 0, "DE"),
            new Dataline(r*Math.cos(8*Math.PI/5), r*Math.sin(8*Math.PI/5), 0, r*Math.cos(0), r*Math.sin(0), 0, "EA"),
            // Triangular faces
            new Dataline(r*Math.cos(0), r*Math.sin(0), 0, 0, 0, h, "A1"),
            new Dataline(r*Math.cos(2*Math.PI/5), r*Math.sin(2*Math.PI/5), 0, 0, 0, h, "B1"),
            new Dataline(r*Math.cos(4*Math.PI/5), r*Math.sin(4*Math.PI/5), 0, 0, 0, h, "C1"),
            new Dataline(r*Math.cos(6*Math.PI/5), r*Math.sin(6*Math.PI/5), 0, 0, 0, h, "D1"),
            new Dataline(r*Math.cos(8*Math.PI/5), r*Math.sin(8*Math.PI/5), 0, 0, 0, h, "E1")
        };
    }
    
}
