/*
 * This file is made available under the terms of the LGPL licence.
 * This licence can be retrieved from http://www.gnu.org/copyleft/lesser.html.
 * The source remains the property of the YAWL Foundation.  The YAWL Foundation is a collaboration of
 * individuals and organisations who are committed to improving workflow technology.
 *
 */

package org.yawlfoundation.yawl.elements;

/**
 *  A simple version numbering implementation stored as a major part and a minor part
 *  (both int) but represented externally as a dotted String (eg 5.12)
 *
 *  @author Michael Adams
 *  Date: 18/10/2007
 *  Last Date: 05/06/08
 */

public class YSpecVersion implements Comparable {
    private int _major ;
    private int _minor ;

    // Constructor with default starting version
    public YSpecVersion() {
        _major = 0 ;
        _minor = 1 ;
    }

    // Constructor with two ints
    public YSpecVersion(int major, int minor) {
        setVersion(major, minor) ;
    }

    // Constructor as string
    public YSpecVersion(String version) {
        setVersion(version);
    }


    public String setVersion(int major, int minor) {
        _major = major ;
        _minor = minor ;
        return toString();
    }

    public String setVersion(String version) {
        String[] part = version.split("\\.");
        try {
            _major = Integer.parseInt(part[0]);
            _minor = Integer.parseInt(part[1]);
        }
        catch (NumberFormatException nfe) {
            throw new NumberFormatException("'" + version +
                                            "' is not a valid version number.");
        }
        return toString();
    }

    public String getVersion() { return toString();}


    public double getVersionAsDouble() {
        try {
            return new Double(toString());
        }
        catch (Exception e) {
            return 0.1 ;                                               // default
        }
    }


    public String toString() {
        return String.format("%s.%s", String.valueOf(_major), String.valueOf(_minor));
    }

    public int getMajorVersion() { return _major; }

    public int getMinorVersion() { return _minor; }

    public String minorIncrement() {
        _minor++ ;
        return toString();
    }

    public String majorIncrement() {
        _major++ ;
        return toString();
    }

    public int compareTo(Object obj) {
        if (obj instanceof YSpecVersion) {
            YSpecVersion other = (YSpecVersion) obj ;

            if (this.equals(other))
                return 0;
            else if (this.equalsMajorVersion(other))
                return this.getMinorVersion() - other.getMinorVersion() ;
            else
                return this.getMajorVersion() - other.getMajorVersion();
        }
        else throw new ClassCastException("Invalid compare of YSpecVersion and " +
                                           obj.toString());
    }
  
    public boolean equalsMajorVersion(YSpecVersion other) {
        return this.getMajorVersion() == other.getMajorVersion();
    }

    public boolean equalsMinorVersion(YSpecVersion other) {
        return this.getMinorVersion() == other.getMinorVersion();
    }

    public boolean equals(YSpecVersion other) {
        return this.equalsMajorVersion(other) && this.equalsMinorVersion(other);
    }
}