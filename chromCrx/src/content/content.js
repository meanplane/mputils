import $ from 'jquery'
import de from "element-ui/src/locale/lang/de";
import Axios from "axios";

// 阿里 搜图 筛选
function ali_filter_data() {
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

            deal = parseInt(deal)
            // 收集数据 发送到页面
            let info = gatherInfo(this)
            info.price = price
            info.deal = deal
            data.push(info)

        } else {
            $(this).attr('style', 'display:none;')
        }
    })

    return {msg: 'ok', data: data}
}

// 淘宝 搜图 筛选
function taobao_filter_data() {

    let data = []
    //过滤没有成交的数据
    $('div#imgsearch-itemlist div.item').each(function () {
        let name = $(this).find('a.pic-link > img').attr('alt')

        let price = $(this).find('div.price strong').text()
        price = parseInt(price)

        let dealCount = $(this).find('div.deal-cnt').text()
        dealCount = parseInt(dealCount.replace('人付款', ''))

        let shopName = $(this).find('div.shop > a.shopname').children('span').eq(1).text()
        let location = $(this).find('div.location').text()
        let shopUrl = 'https:' + $(this).find('div.shop > a.shopname').attr('href')
        let img = 'https:' + $(this).find('a.pic-link > img').attr('data-src')
        let goodsUrl = 'https:' + $(this).find('a.pic-link').attr('href')

        data.push({img, name, goodsUrl, price, dealCount, shopName, shopUrl, location})
    })

    return {msg: 'ok', data: data}
}

function check_host() {
    let data = {host: window.location.host}
    console.log('host :', data)
    return data
}

function test(param) {
    console.log("test 函数 执行 ...", param)

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
        let func = eval(request.type)
        let res = func(request.params)

        // 返回执行结果
        if (sendResponse && res) {
            sendResponse(res)
        }
    }
);