package com.au.service;

import java.util.List;

public interface URLPinger {
    void pingUrl(final String[] urls) throws Exception;
    List<String> findAllUrlWithCookies(final String attr);
}
