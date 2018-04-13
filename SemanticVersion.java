import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SemanticVersion implements Comparable<SemanticVersion> {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SemanticVersion.class);

  private Integer major;
  private Integer minor;
  private Integer patch;

  public SemanticVersion(String version) {
    String[] parts = version.split("\\.");
    major = parse(parts, 0);
    minor = parse(parts, 1);
    patch = parse(parts, 2);
  }

  public SemanticVersion(Integer major, Integer minor, Integer patch) {
    this.major = major;
    this.minor = minor;
    this.patch = patch;
  }

  private Integer parse(String[] parts, int index) {
    Integer result = 0;
    if (parts.length > index) {
      try {
        result = Integer.valueOf(parts[index]);
      } catch (NumberFormatException e) {
        LOGGER.debug("unknownVersionFormat: " + parts[index], e);
      }
    }
    return result;
  }

  @Override
  public int compareTo(SemanticVersion that) {
    if (this.major != that.major) {
      return this.major - that.major;
    }
    if (this.minor != that.minor) {
      return this.minor - that.minor;
    }
    if (this.patch != that.patch) {
      return this.patch - that.patch;
    }
    return 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((major == null) ? 0 : major.hashCode());
    result = (prime * result) + ((minor == null) ? 0 : minor.hashCode());
    result = (prime * result) + ((patch == null) ? 0 : patch.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SemanticVersion other = (SemanticVersion) obj;
    if (major == null) {
      if (other.major != null) {
        return false;
      }
    } else if (!major.equals(other.major)) {
      return false;
    }
    if (minor == null) {
      if (other.minor != null) {
        return false;
      }
    } else if (!minor.equals(other.minor)) {
      return false;
    }
    if (patch == null) {
      if (other.patch != null) {
        return false;
      }
    } else if (!patch.equals(other.patch)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "{\"class\":\"SemanticVersion\",\"major\":" + major + ",\"minor\":" + minor
        + ",\"patch\":" + patch + "}";
  }
}
