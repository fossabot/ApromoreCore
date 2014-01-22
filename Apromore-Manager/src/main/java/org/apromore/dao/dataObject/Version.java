package org.apromore.dao.dataObject;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Version identifier for capabilities such as bundles and packages.
 * <p/>
 * <p/>
 * Version identifiers have four components.
 * <ol>
 * <li>Major version. A non-negative integer.</li>
 * <li>Minor version. A non-negative integer.</li>
 * <li>Micro version. A non-negative integer.</li>
 * <li>Qualifier. A text string. See {@code Version(String)} for the format of
 * the qualifier string.</li>
 * </ol>
 * <p/>
 * <p/>
 * {@code Version} objects are immutable.
 */
public class Version implements Comparable<Version> {
    private final Integer major;
    private final Integer minor;
    private final Integer micro;
    private final String qualifier;
    private static final String SEPARATOR = ".";
    private transient String versionString /* default to null */;
    private transient int hash /* default to 0 */;

    /**
     * The empty version "0.0".
     */
    public static final Version emptyVersion = new Version(0, 0);

    /**
     * Creates a version identifier from the specified numerical components.
     * <p/>
     * <p/>
     * The qualifier is set to the empty string.
     *
     * @param major Major component of the version identifier.
     * @param minor Minor component of the version identifier.
     * @throws IllegalArgumentException If the numerical components are
     *                                  negative.
     */
    public Version(Integer major, Integer minor) {
        this(major, minor, null, null);
    }

    /**
     * Creates a version identifier from the specified numerical components.
     * <p/>
     * <p/>
     * The qualifier is set to the empty string.
     *
     * @param major Major component of the version identifier.
     * @param minor Minor component of the version identifier.
     * @param micro Micro component of the version identifier.
     * @throws IllegalArgumentException If the numerical components are
     *                                  negative.
     */
    public Version(Integer major, Integer minor, Integer micro) {
        this(major, minor, micro, null);
    }

    /**
     * Creates a version identifier from the specified components.
     *
     * @param major     Major component of the version identifier.
     * @param minor     Minor component of the version identifier.
     * @param micro     Micro component of the version identifier.
     * @param qualifier Qualifier component of the version identifier. If
     *                  {@code null} is specified, then the qualifier will be set to the
     *                  empty string.
     * @throws IllegalArgumentException If the numerical components are negative
     *                                  or the qualifier string is invalid.
     */
    public Version(Integer major, Integer minor, Integer micro, String qualifier) {
        if (qualifier == null) {
            qualifier = "";
        }

        this.major = major;
        this.minor = minor;
        this.micro = micro;
        this.qualifier = qualifier;
        validate();
    }

    /**
     * Creates a version identifier from the specified string.
     * <p/>
     * <p/>
     * Version string grammar:
     * <p/>
     * <pre>
     * version ::= major('.'minor('.'micro('.'qualifier)?)?)?
     * major ::= digit+
     * minor ::= digit+
     * micro ::= digit+
     * qualifier ::= (alpha|digit|'_'|'-')+
     * digit ::= [0..9]
     * alpha ::= [a..zA..Z]
     * </pre>
     *
     * @param version String representation of the version identifier. There
     *                must be no whitespace in the argument.
     * @throws IllegalArgumentException If {@code version} is improperly
     *                                  formatted.
     */
    public Version(String version) {
        Integer maj;
        Integer min = null;
        Integer mic = null;
        String qual = "";

        try {
            StringTokenizer st = new StringTokenizer(version, SEPARATOR, true);
            maj = parseInt(st.nextToken(), version);

            if (st.hasMoreTokens()) { // minor
                st.nextToken(); // consume delimiter
                min = parseInt(st.nextToken(), version);

                if (st.hasMoreTokens()) { // micro
                    st.nextToken(); // consume delimiter
                    mic = parseInt(st.nextToken(), version);

                    if (st.hasMoreTokens()) { // qualifier separator
                        st.nextToken(); // consume delimiter
                        qual = st.nextToken(""); // remaining string

                        if (st.hasMoreTokens()) { // fail safe
                            throw new IllegalArgumentException("invalid version \"" + version + "\": invalid format");
                        }
                    }
                }
            }
        } catch (NoSuchElementException e) {
            IllegalArgumentException iae = new IllegalArgumentException("invalid version \"" + version + "\": invalid format");
            iae.initCause(e);
            throw iae;
        }

        major = maj;
        minor = min;
        micro = mic;
        qualifier = qual;
        validate();
    }

    /**
     * Parse numeric component into an int.
     *
     * @param value   Numeric component
     * @param version Complete version string for exception message, if any
     * @return int value of numeric component
     */
    private static int parseInt(String value, String version) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            IllegalArgumentException iae = new IllegalArgumentException("invalid version \"" + version + "\": non-numeric \"" + value + "\"");
            iae.initCause(e);
            throw iae;
        }
    }

    /**
     * Called by the Version constructors to validate the version components.
     *
     * @throws IllegalArgumentException If the numerical components are negative
     *                                  or the qualifier string is invalid.
     */
    private void validate() {
        if (major < 0) {
            throw new IllegalArgumentException("invalid version \"" + toString0() + "\": negative number \"" + major + "\"");
        }
        if (micro != null && minor < 0) {
            throw new IllegalArgumentException("invalid version \"" + toString0() + "\": negative number \"" + minor + "\"");
        }
        if (micro != null && micro < 0) {
            throw new IllegalArgumentException("invalid version \"" + toString0() + "\": negative number \"" + micro + "\"");
        }
        for (char ch : qualifier.toCharArray()) {
            if (('A' <= ch) && (ch <= 'Z')) {
                continue;
            }
            if (('a' <= ch) && (ch <= 'z')) {
                continue;
            }
            if (('0' <= ch) && (ch <= '9')) {
                continue;
            }
            if ((ch == '_') || (ch == '-')) {
                continue;
            }
            throw new IllegalArgumentException("invalid version \"" + toString0() + "\": invalid qualifier \"" + qualifier + "\"");
        }
    }

    /**
     * Parses a version identifier from the specified string.
     * <p/>
     * <p/>
     * See {@code Version(String)} for the format of the version string.
     *
     * @param version String representation of the version identifier. Leading
     *                and trailing whitespace will be ignored.
     * @return A {@code Version} object representing the version identifier. If
     * {@code version} is {@code null} or the empty string then
     * {@code emptyVersion} will be returned.
     * @throws IllegalArgumentException If {@code version} is improperly
     *                                  formatted.
     */
    public static Version parseVersion(String version) {
        if (version == null) {
            return emptyVersion;
        }

        version = version.trim();
        if (version.length() == 0) {
            return emptyVersion;
        }

        return new Version(version);
    }

    /**
     * Returns the major component of this version identifier.
     *
     * @return The major component.
     */
    public Integer getMajor() {
        return major;
    }

    /**
     * Returns the minor component of this version identifier.
     *
     * @return The minor component.
     */
    public Integer getMinor() {
        return minor;
    }

    /**
     * Returns the micro component of this version identifier.
     *
     * @return The micro component.
     */
    public Integer getMicro() {
        return micro;
    }

    /**
     * Returns the qualifier component of this version identifier.
     *
     * @return The qualifier component.
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * Returns the string representation of this version identifier.
     * <p/>
     * <p/>
     * The format of the version string will be {@code major.minor.micro} if
     * qualifier is the empty string or {@code major.minor.micro.qualifier}
     * otherwise.
     *
     * @return The string representation of this version identifier.
     */
    public String toString() {
        return toString0();
    }

    /**
     * Internal toString behavior
     *
     * @return The string representation of this version identifier.
     */
    String toString0() {
        if (versionString != null) {
            return versionString;
        }
        int q = qualifier.length();
        StringBuilder result = new StringBuilder(20 + q);
        result.append(major);
        if (minor != null) {
            result.append(SEPARATOR);
            result.append(minor);
        }
        if (micro != null) {
            result.append(SEPARATOR);
            result.append(micro);
        }
        if (q > 0) {
            result.append(SEPARATOR);
            result.append(qualifier);
        }
        return versionString = result.toString();
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return An integer which is a hash code value for this object.
     */
    public int hashCode() {
        if (hash != 0) {
            return hash;
        }
        int h = 31 * 17;
        h = 31 * h + major;
        h = 31 * h + minor;
        h = 31 * h + micro;
        h = 31 * h + qualifier.hashCode();
        return hash = h;
    }

    /**
     * Compares this {@code Version} object to another object.
     * <p/>
     * <p/>
     * A version is considered to be <b>equal to </b> another version if the
     * major, minor and micro components are equal and the qualifier component
     * is equal (using {@code String.equals}).
     *
     * @param object The {@code Version} object to be compared.
     * @return {@code true} if {@code object} is a {@code Version} and is equal
     * to this object; {@code false} otherwise.
     */
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Version)) {
            return false;
        }

        Version other = (Version) object;
        return new EqualsBuilder()
                .append(major, other.major)
                .append(minor, other.minor)
                .append(micro, other.micro)
                .append(qualifier, other.qualifier).isEquals();
    }

    /**
     * Compares this {@code Version} object to another {@code Version}.
     * <p/>
     * <p/>
     * A version is considered to be <b>less than</b> another version if its
     * major component is less than the other version's major component, or the
     * major components are equal and its minor component is less than the other
     * version's minor component, or the major and minor components are equal
     * and its micro component is less than the other version's micro component,
     * or the major, minor and micro components are equal and it's qualifier
     * component is less than the other version's qualifier component (using
     * {@code String.compareTo}).
     * <p/>
     * <p/>
     * A version is considered to be <b>equal to</b> another version if the
     * major, minor and micro components are equal and the qualifier component
     * is equal (using {@code String.compareTo}).
     *
     * @param other The {@code Version} object to be compared.
     * @return A negative integer, zero, or a positive integer if this version
     * is less than, equal to, or greater than the specified
     * {@code Version} object.
     * @throws ClassCastException If the specified object is not a
     *                            {@code Version} object.
     */
    @Override
    public int compareTo(Version other) {
        if (other == this) { // quicktest
            return 0;
        }

        int result = major - other.major;
        if (result != 0) {
            return result;
        }

        result = minor - other.minor;
        if (result != 0) {
            return result;
        }

        result = micro - other.micro;
        if (result != 0) {
            return result;
        }

        return qualifier.compareTo(other.qualifier);
    }
}
