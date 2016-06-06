# share2weixin
微信公众号开发－分享给微信好友和朋友圈

第一步 获取access_token

https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf61eb1870dcxxxxx&secret=5307433c787d335be5f59cxxxxxx

结果：
{
  "access_token": "O-HGeE3QEqxy5jeS5YhpjGJ4QEAoCPSD2MM5NJ5_Iev_dcvHUygDdeMZ2GAu1yPF6qnB8V5yHCxZ5ZOrmqzP8hQfNzb3m2JMWLGOZ4IkUtQ1eEtN-auXbAPt_F9E6M1OWLDeAEACRJ",
  "expires_in": 7200
}

第二步 获取ticket

https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=wov-yYB7GLkaghUjQQayWVHR6vcdim86FSf9TdvHklMUfruo5k-MxeXgU1xnoDfiHk8m794YVUjBN4mkgShOPP-Q7rcssDyNEZJGc3ZnYlNqmFeqGCafVk9H7cg8BmmVPFUhAIAWMB&type=jsapi

结果：
{
  "errcode": 0,
  "errmsg": "ok",
  "ticket": "sM4AOVdWfPE4DxkXGEs8VH5bGDJtWjsIzLJK38VUVFVygH-4-MvRIXpxbcVmEYHVTxQQq8YOfDtavy6zzlvWkg",
  "expires_in": 7200
}

第三步 签名算法实现
debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
appId: '', // 必填，公众号的唯一标识
timestamp: , // 必填，生成签名的时间戳
nonceStr: '', // 必填，生成签名的随机串
signature: '',// 必填，签名
api文档地址
<p>http://mp.weixin.qq.com/wiki/home/</p>
