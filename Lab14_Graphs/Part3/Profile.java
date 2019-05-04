import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that creates a user profile.
 *
 * @author Robert Aroutiounian
 */
public class Profile implements Comparable
{
    private String name;
    private String status;
    private List<Profile> friends;

    public Profile()
    {
        this.name = "";
        this.status = "";
        this.friends = new ArrayList<>();
    }

    public Profile(String name, String status)
    {
        setName(name);
        setStatus(status);
        this.friends = new ArrayList<>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getName()
    {
        return this.name;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void addFriend(String name, String status)
    {
        Profile profile = new Profile(name, status);
        this.friends.add(profile);
    }

    public List<Profile> getFriends()
    {
        return this.friends;
    }

    public int compareTo(Object other)
    {
        Profile otherProfile = (Profile)other;
        int result;

        int nameCompare = this.getName().compareTo(otherProfile.getName());
        if (nameCompare == 0)
        {
            result = this.getStatus().compareTo(otherProfile.getStatus());
        }
        else
        {
            result = nameCompare;
        }
        return result;
    }
}
