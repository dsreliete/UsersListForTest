package com.challenge.buscape.buscapeuserslist.users;

import com.challenge.buscape.buscapeuserslist.data.model.Address;
import com.challenge.buscape.buscapeuserslist.data.model.User;
import com.challenge.buscape.buscapeuserslist.data.webservice.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eliete on 7/28/16.
 */
public class UsersPresenterTest {

    @Mock
    UserRepository userRepository;

    @Mock
    MainActivity mainActivity;

    @Captor
    ArgumentCaptor<UserRepository.getListOnFinishedListener>
            getListOnFinishedListenerArgumentCaptor;

    @Mock
    MainPresenter userPresenter;

    private static List<User> userList = Arrays.asList(
            new User(1, "eliete", "xhf@gmail.com", new Address(), "11987455", "www.com.br"),
            new User(1, "eliete", "xhf@gmail.com", new Address(), "11987455", "www.com.br"));

    @Before
    public void setupUserPresenter(){
        MockitoAnnotations.initMocks(this);
        userPresenter = new MainPresenter(mainActivity, userRepository);
    }

    @Test
    public void clickOnItem_ShowsDetailUser() {
        User user = new User(1, "eliete", "xhf@gmail.com", new Address(), "11987455", "www.com.br");
        userPresenter.openItemDetails(user);
        Mockito.verify(mainActivity).showUserDetailActivity(Mockito.any(User.class));
    }

    @Test
    public void loadUserFromRepositoryAndLoadIntoView(){
        userPresenter.fetchUserList();
        Mockito.verify(userRepository).getUsersList(getListOnFinishedListenerArgumentCaptor.capture());
        getListOnFinishedListenerArgumentCaptor.getValue().onFinishedList(userList);
        Mockito.verify(mainActivity).hideProgress();
        Mockito.verify(mainActivity).showUsersList(userList);
    }


}
