import angular from 'angular';

export const AlertModule = angular.module('alert.module', [])

.service('AlertService', class AlertService {
    constructor($timeout) {
        this.alert = {
            message: '',
            level: ''
        }
        this.$timeout = $timeout;
    }
    addAlert(message, level) {
        this.alert.message = message;
        this.alert.level = 'alert-' + level;
        this.$timeout(7000).then(() => this.alert.message = '');
    }
})
.controller('AlertController', class AlertController {
    constructor(AlertService) {
        this.alert = AlertService.alert;
    }
    close() {
        this.alert.message = '';
    }
})

.name;