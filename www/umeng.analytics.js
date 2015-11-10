var exec = require('cordova/exec');

module.exports = {
    Analytics: {
        config: function (parameters, onSuccess, onError) {
            exec(onSuccess, onError, "Analytics", "config", [parameters]);
        },

        startPage: function(page, onSuccess, onError) {
            exec(onSuccess, onError, "Analytics", "startPage", [page]);
        },

        endPage: function(page, onSuccess, onError) {
        	exec(onSuccess, onError, "Analytics", "endPage", [page]);
        },

        setDebug: function(enable, onSuccess, onError) {
        	exec(onSuccess, onError, "Analytics", "setDebug", [enable]);	
        }.

        logEvent: function (parameters, onSuccess, onError) {
            exec(onSuccess, onError, "Analytics", "logEvent", [parameters]);
        }
    }
};
