import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SemanticVersionTest {

  private SemanticVersion subject;

  @Test
  public void compare_differingMajor_highMajorGreater() {

    assertThat(new SemanticVersion(2, 0, 0).compareTo(new SemanticVersion(1, 0, 0)),
        is(greaterThan(0)));

  }

  @Test
  public void compare_differingMinor_highMinorGreater() {
    assertThat(new SemanticVersion(2, 1, 0).compareTo(new SemanticVersion(2, 0, 0)),
        is(greaterThan(0)));
  }


  @Test
  public void compare_differingPatch_highPatchGreater() {
    assertThat(new SemanticVersion(1, 0, 4).compareTo(new SemanticVersion(1, 0, 2)),
        is(greaterThan(0)));
  }

  @Test
  public void compare_lowMajorHighMinor_highMajorReturned() {
    assertThat(new SemanticVersion(1, 100, 0).compareTo(new SemanticVersion(2, 10, 2)),
        is(lessThan(0)));
  }

  @Test
  public void compare_same_equal() {
    assertThat(new SemanticVersion(1, 100, 234).compareTo(new SemanticVersion(1, 100, 234)), is(0));
  }

  @Test
  public void equal_same_equal() {
    assertThat(new SemanticVersion(1, 100, 234), is(new SemanticVersion(1, 100, 234)));
  }

  @Test
  public void hascode_same_equal() {
    assertThat(new SemanticVersion(1, 100, 234).hashCode(),
        is(new SemanticVersion(1, 100, 234).hashCode()));
  }

  @Test
  public void equal_different_notEqual() {
    assertThat(new SemanticVersion(1, 100, 234), is(not(new SemanticVersion(0, 100, 234))));
  }

  @Test
  public void hascode_different_notEqual() {
    assertThat(new SemanticVersion(1, 100, 234).hashCode(),
        is(not(new SemanticVersion(9, 9, 9).hashCode())));
  }
}
