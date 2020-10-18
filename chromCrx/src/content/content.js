import $ from 'jquery'
import de from "element-ui/src/locale/lang/de";
import Axios from "axios";


function filter_ali_data(params) {
    console.log('params', params)

    let gatherInfo = function (a) {
        let goodsUrl = $(a).find('div.img-container a').attr('href');
        let img = $(a).find('div.img-container div.img').attr('style');

        let begin = img.indexOf('url("') + 5
        let end = img.indexOf('");')
        img = img.substring(begin, end)


        let name = $(a).find('div.desc-container div.title').text();
        let rebuy = $(a).find('div.desc-container span.shop-repurchase-rate').text();

        if (rebuy) {
            rebuy = rebuy.replace('%', '')
            rebuy = rebuy.replace('复购率', '')
        } else {
            rebuy = 0
        }
        rebuy = parseFloat(rebuy)

        let corp = $(a).find('div.company-tag-container div.company-name').attr('title');

        return {goodsUrl, rebuy, corp, img, name}
    }

    let data = []
    //过滤没有成交的数据
    $('div.card-container').each(function () {
        let deal = $(this).find('div.sale').text()
        let price = $(this).find('div.price').text()
        price = parseFloat(price)

        if (deal) {

            deal = deal.replace('成交', '')
            deal = deal.replace('元', '')
            if (deal.includes('万')) {
                deal = deal.replace('万', '')
                deal *= 10000
            }

            // 过滤价格
            if (parseFloat(price) < parseFloat(params.beginPrice) || parseFloat(price) > parseFloat(params.endPrice)) {
                $(this).attr('style', 'display:none;')
            } else {
                deal = parseInt(deal)
                // 收集数据 发送到页面
                let info = gatherInfo(this)
                info.price = price
                info.deal = deal
                data.push(info)
            }

        } else {
            $(this).attr('style', 'display:none;')
        }
    })

    return {msg: 'ok', data: data}
}

function test() {
    console.log("test 函数 执行 ...")
    Axios.post('http://localhost:8080/index').then(function (res) {
        console.log(res);
    })
    return 'ok'
}


// 监听消息
chrome.extension.onMessage.addListener(
    function (request, sender, sendResponse) {
        console.log('content 受到数据', request)

        // 处理数据
        let res = undefined
        if (request.type === 'test') {
            res = test()
        } else if (request.type === 'filter_ali_data') {
            res = filter_ali_data(request.params)
        }

        // 返回执行结果
        if (sendResponse && res) {
            sendResponse(res)
        }
    }
);