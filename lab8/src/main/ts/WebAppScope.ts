/// <reference path="../../../typings/angularjs/angular.d.ts" />
/// <reference path="user/User.ts"/>

module tutorial.webapp {
    export interface WebAppScope extends ng.IScope {
        users : User[];
        refreshUsers(): void;
    }
}