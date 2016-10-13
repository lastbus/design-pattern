package com.disanyuzhou.designpattern.filter.request.chain;

import java.util.List;

/**
 * Created by make on 6/14/16.
 */
public interface Command {
    public List<Goods> execute(String key);
}
