package com.family.webserver.rbac;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by chen on 2014/7/21.
 */
@SuppressWarnings("ALL")

public class ShiroDbRealm extends AuthorizingRealm {

  @Override
  /**
   * 回调函数
   */
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


    return null;
  }

  /**
   * 登录时调用
   *
   * @param authenticationToken
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;


    return null;

  }
}
