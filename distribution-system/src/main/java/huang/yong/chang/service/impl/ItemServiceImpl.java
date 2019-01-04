package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Integral;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;
import huang.yong.chang.mapper.ItemMapper;
import huang.yong.chang.service.ItemService;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemMapper> implements ItemService {
    @Override
    public IPage<Item> findPage(ItemPageRequest itemPageRequest) {

        Page<Item> itemPage = new Page<>(itemPageRequest.getPage(),itemPageRequest.getPageSize());
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(itemPageRequest.getName())) {
            itemQueryWrapper.like("name", itemPageRequest.getName());
        }

        if (itemPageRequest.getIsAsc() != null && StringUtils.isNotEmpty(itemPageRequest.getOrderByColumn()) ) {
            itemQueryWrapper.orderBy(true, itemPageRequest.getIsAsc(), itemPageRequest.getOrderByColumn());
        }

        return mapper.selectPage(itemPage,itemQueryWrapper);
    }

    @Override
    public List<Item> findIdsByName(String itemName) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.like("name", itemName);
        return mapper.selectList(itemQueryWrapper);
    }
}
