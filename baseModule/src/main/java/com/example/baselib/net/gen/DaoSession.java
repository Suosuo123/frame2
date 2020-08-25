package com.example.baselib.net.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.baselib.net.cookie.CookieResulte;
import com.example.baselib.net.download.DownInfo;

import com.example.baselib.net.gen.CookieResulteDao;
import com.example.baselib.net.gen.DownInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cookieResulteDaoConfig;
    private final DaoConfig downInfoDaoConfig;

    private final CookieResulteDao cookieResulteDao;
    private final DownInfoDao downInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cookieResulteDaoConfig = daoConfigMap.get(CookieResulteDao.class).clone();
        cookieResulteDaoConfig.initIdentityScope(type);

        downInfoDaoConfig = daoConfigMap.get(DownInfoDao.class).clone();
        downInfoDaoConfig.initIdentityScope(type);

        cookieResulteDao = new CookieResulteDao(cookieResulteDaoConfig, this);
        downInfoDao = new DownInfoDao(downInfoDaoConfig, this);

        registerDao(CookieResulte.class, cookieResulteDao);
        registerDao(DownInfo.class, downInfoDao);
    }
    
    public void clear() {
        cookieResulteDaoConfig.clearIdentityScope();
        downInfoDaoConfig.clearIdentityScope();
    }

    public CookieResulteDao getCookieResulteDao() {
        return cookieResulteDao;
    }

    public DownInfoDao getDownInfoDao() {
        return downInfoDao;
    }

}