$(function () {
    Date.prototype.Format = function (a) {
        var c = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(a)) {
            a = a.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
        }
        for (var b in c) {
            if (new RegExp("(" + b + ")").test(a)) {
                a = a.replace(RegExp.$1, (RegExp.$1.length === 1) ? (c[b]) : (("00" + c[b]).substr(("" + c[b]).length)))
            }
        }
        return a
    }
});