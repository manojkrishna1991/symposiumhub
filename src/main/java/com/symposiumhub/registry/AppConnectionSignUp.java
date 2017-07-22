package com.symposiumhub.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.symposiumhub.dto.LocalUser;
import com.symposiumhub.dto.SocialProvider;
import com.symposiumhub.dto.UserRegistrationForm;
import com.symposiumhub.service.UserService;
import com.symposiumhub.util.SecurityUtil;

/**
 * If no local user associated with the given connection then
 * connection signup will create a new local user from the given connection.
 *
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 27/3/16
 */
public class AppConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserService userService;

    @Override
    public String execute(final Connection<?> connection) {
    	connection.fetchUserProfile().getEmail();
        UserRegistrationForm userDetails = toUserRegistrationObject(connection.getKey().getProviderUserId(), SecurityUtil.toSocialProvider(connection.getKey().getProviderId()), connection.fetchUserProfile());
        LocalUser user = (LocalUser) userService.registerNewUser(userDetails);
        return user.getUserId().toString();
    }

    private UserRegistrationForm toUserRegistrationObject(final String userId, final SocialProvider socialProvider, final UserProfile userProfile) {
        return UserRegistrationForm.getBuilder()
                .addUserId(userId)
                .addFirstName(userProfile.getName())
                .addEmail(userProfile.getEmail())
                .addPassword(userProfile.getName())
                .addSocialProvider(socialProvider).build();
    }

}
