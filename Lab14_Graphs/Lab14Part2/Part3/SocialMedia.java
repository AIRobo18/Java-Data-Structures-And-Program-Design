import java.lang.ref.PhantomReference;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

/**
 * A class that creates a basic social media platform.
 *
 * @author Robert Aroutiounian
 * @version 12/4/2015
 */
public class SocialMedia
{
    private Profile profile;
    private LinkedHashMap<Integer, Profile> userFreinds;
    private int id;
    private final int CURRENT_ID = 0;
    private final String[] STATUS = {"happy", "sad", "ecstatic", "depressed", "mad", "content", "surprised", "annoyed"};

    public SocialMedia()
    {
        this.userFreinds = new LinkedHashMap<>();
        this.id = 0;
        this.profile = new Profile();

        Scanner nameScanner = new Scanner(System.in);
        Scanner statusScanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = nameScanner.nextLine();
        for (int i = 0; i < this.STATUS.length; i++)
        {
            System.out.print(this.STATUS[i] + " ");
        }
        System.out.println("\nHow are you feeling --> ");
        String status = statusScanner.next().toLowerCase();
        createProfile(name, status);

        menu();
    }

    public void menu()
    {
        Scanner scan = new Scanner(System.in);
        char choice;
            System.out.println( "\nA. Modify profile" +
                    "\nB. Search for other profile" +
                    "\nC. Add friend" +
                    "\nD. Get friend's friend list" +
                    "\nE. Send out emergency calls" +
                    "\nF. Find closest friend" +
                    "\nG. Quit" +
                    "\nWhat would you like to do (Please type letter):");
            choice = scan.next().toUpperCase().charAt(0);

        run(choice);
    }

    public void run(int choice)
    {
        Scanner nameScanner = new Scanner(System.in);
        Scanner statusScanner = new Scanner(System.in);

        switch (choice)
        {
            case 'A':
                System.out.println("What would you like your new name to be -->");
                String newName = nameScanner.next();
                for (int i = 0; i < this.STATUS.length; i++)
                {
                    System.out.print(this.STATUS[i] + " ");
                }
                System.out.println("\nHow are you feeling --> ");
                String newStatus = statusScanner.next().toLowerCase();
                modifyProfile(newName, newStatus);
                break;
            case 'B':
                for (int i = 0; i < this.STATUS.length; i++)
                {
                    System.out.print(this.STATUS[i] + " ");
                }
                System.out.println();
                System.out.println("What status are you looking for: ");
                String statusSearch = statusScanner.next();
                searchForUser(statusSearch);
                break;
            case 'C':
                System.out.println("Enter user name you would like to add as friend: ");
                String userName = nameScanner.next();
                addFriend(userName);
                break;
            case 'D':
                System.out.println("Getting your friends list");
                getFriendList();
                break;
            case 'E':
                System.out.println("Sending out emergency calls!!!!!!! DON'T WORRY, HELP IS COMING SOON");
                emergencyContactChain();
                break;
            case 'G':
                System.out.println("Logging out. Good bye.");
                break;
            default:
                menu();
                break;

        }
    }

    //return user not void
    public void createProfile(String name, String status)
    {
        this.profile = new Profile(name,status);
        this.userFreinds.put(this.id, this.profile);
        this.id++;

        menu();
    }

    public void modifyProfile(String newName, String newStatus)
    {
        Profile profile = new Profile(newName, newStatus);
        this.profile = profile;
        this.userFreinds.put(this.CURRENT_ID, this.profile);

        menu();
    }

    public void searchForUser(String status)
    {
        searchStatus(status);

        menu();
    }

    public Set<Map.Entry<Integer, Profile>> searchStatus(String status)
    {
        LinkedHashMap<Integer, Profile> resultMap = new LinkedHashMap<>();
        Set<Map.Entry<Integer, Profile>> entrySet = this.userFreinds.entrySet();
        for (Map.Entry<Integer, Profile> entry: entrySet)
        {
            Profile current = entry.getValue();
            if (current.getStatus().equals(status))
            {
                resultMap.put(entry.getKey(), current);
            }
        }

        return resultMap.entrySet();
    }

    public void addFriend(String userName)
    {
        Random ran = new Random();

        Profile profile = this.userFreinds.get(this.CURRENT_ID);
        profile.addFriend(userName, this.STATUS[ran.nextInt(this.STATUS.length)]);

        menu();
    }

    public void getFriendList()
    {
        friendList();
        menu();
    }

    public List<Profile> friendList()
    {
        Profile profile = this.userFreinds.get(this.CURRENT_ID);
        List<Profile> friends = profile.getFriends();
        List<Profile> resultList = new ArrayList<>();
        for (Profile friend: friends)
        {
            resultList.addAll(friend.getFriends());
        }

        return resultList;
    }

    public void emergencyContactChain()
    {
        Profile root = this.userFreinds.get(this.CURRENT_ID);
        Set<Profile> profileSet = new LinkedHashSet<>();
        Queue<Profile> profileQueue = new LinkedList<>();
        profileSet.add(root);
        profileQueue.offer(root);
        while (!profileQueue.isEmpty())
        {
            Profile current = profileQueue.poll();
            List<Profile> friends = current.getFriends();
            profileSet.addAll(friends);
            for (Profile friend: friends)
            {
                if (!profileSet.contains(friend))
                {
                    profileQueue.offer(friend);
                }
            }
        }

        menu();
    }

    public static void main(String[] args)
    {
        SocialMedia socialMedia = new SocialMedia();
    }
}
