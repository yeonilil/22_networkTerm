/*package network_term;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendManager implements FriendManagerInterface{
	private List<Friend> friendList;

    public FriendManager() {
        friendList = new ArrayList<Friend>();
    }

    public void addFriend(Friend friend) {
        friendList.add(friend);
    }

    public List<Friend> searchFriend(String name) {
        List<Friend> searchList = new ArrayList<Friend>();
        for(Friend friend : friendList){
            if(friend.getName().equals(name)){
                searchList.add(friend);
            }
        }

        return searchList;
    }

    public int deleteFriend(String phone) {
//        for(Friend friend : friendList){
//            if(friend.getPhone().equals(phone)){
//                friendList.remove(friend);
//            }
//        }
        /*
        int size = friendLIst.size();
        for(int i = 0; i < size ; i++){
            if(phone.equals(friendList[i].getPhone()){
                remove(friendList[i];
                }
        }
        */
/*
        int deleteCount = 0;
        Iterator<Friend> iterator = friendList.iterator();
        while(iterator.hasNext()){
            Friend friend = iterator.next();
            if(friend.getPhone().equals(phone)){
                iterator.remove();
                deleteCount++;
            }
        }
        return deleteCount;
    }

    public int countFriend() {
        return friendList.size();
    }
}*/
