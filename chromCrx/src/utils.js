const host = "http://localhost:8080/"

// 向content js 发送消息
function sendMsg(msg, responseFunc) {
    chrome.tabs.query({active: true, currentWindow: true}, function (tabs) {
        chrome.tabs.sendMessage(tabs[0].id, msg, function (response) {
            if (responseFunc) {
                responseFunc(response)
            }
        });//end  sendMessage
    }); //end query
}

import axios from "axios";

// 向本地服务发送消息
function axGet(url, params) {
    // return axios.get(host + url, params)
    return axios({
        method: 'get',
        url: host + url,
        params: params
    })
}

function axPost(url, params) {
    return axios.post(host + url, params)
}

export {
    sendMsg, axGet, axPost
}


