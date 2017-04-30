package soundsystem;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component("lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";  
  private String artist = "The Beatles";
  List<String> tracks;

  public void setTracks(List<String> tracks) {
    this.tracks = tracks;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }

  @Override
  public void playTrack(int trackNumber) {
    System.out.println(tracks.get(trackNumber));
  }


}
