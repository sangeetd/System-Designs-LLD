package com.sangeetdas.project.managers;

import com.sangeetdas.project.chat.SendingPolicyType;
import com.sangeetdas.project.exceptions.ServerNotAvailableException;
import com.sangeetdas.project.exceptions.UnableToRetrieveChatException;
import com.sangeetdas.project.exceptions.UserNotRegisteredException;
import com.sangeetdas.project.models.AccountDetails;
import com.sangeetdas.project.models.Contact;
import com.sangeetdas.project.messages.Message;
import com.sangeetdas.project.serverregistry.ServerRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.sangeetdas.project.ChatAppLldApplication.getServerRegistry;

public class AccountManager {

    private final ServerRegistry serverRegistry;

    private AccountDetails userAccountDetails;
    private final List<Contact> userContacts;
    private final ChatManager userChatManager;
    private SendingPolicyType sendingPolicyType;

    public AccountManager() {
        this.serverRegistry = getServerRegistry();
        this.userContacts = new ArrayList<>();
        this.userChatManager = new ChatManager();
        this.sendingPolicyType = SendingPolicyType.INTERNET_BASED; //default
    }

    public AccountDetails getUserAccountDetails() {
        return userAccountDetails;
    }

    //create account
    public void createAccount(final String contactNo, final String firstName){

        this.userAccountDetails = new AccountDetails(new Contact(contactNo, firstName));
        if(this.serverRegistry == null){
            throw new ServerNotAvailableException();
        }
        this.serverRegistry.registerChatManager(contactNo, this.userChatManager);

    }
    //add contact
    public void addContact(final String contactNo, final String firstName){
        this.userContacts.add(new Contact(contactNo, firstName));
    }
    //change sending policy type at runtime
    public void changeSendingPolicyType(SendingPolicyType sendingPolicyType){
        this.sendingPolicyType = sendingPolicyType;
    }
    //send message to contact
    public void send(String to, Message message){

        if(!this.serverRegistry.globalUserExist(to)){
            throw new UserNotRegisteredException();
        }

        this.userChatManager.forwardOneToOne(this.userAccountDetails.getUserContact().getContactNo(),
                to,
                message,
                this.sendingPolicyType);
    }
    //retrieve messages
    public List<Message> retrieveMessage(String contactNo){

        if(!this.userChatManager.chatWindowExist(contactNo)){
            throw new UnableToRetrieveChatException(contactNo);
        }

        return this.userChatManager.getUserActiveChats().get(contactNo).showMessages();

    }



}
