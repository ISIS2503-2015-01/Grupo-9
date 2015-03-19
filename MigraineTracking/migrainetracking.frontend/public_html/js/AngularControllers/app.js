/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var aplicacionMundial= angular.module('aplicacionMundial',[]);
    aplicacionMundial.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });
    aplicacionMundial.directive('competitorInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/competitor-info.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                    $http.get('URL del Servicio').success(function(data){
                        self.competitors=data;
                    });
            }],
            controllerAs:'getCompetitors'
        };
    });
    aplicacionMundial.directive('competitorForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/competitor-form.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitor={};
                this.addCompetitor=function(){
                    $http.post('URL del Servicio', JSON.stringify(self.competitor)).success(function(data){
                        self.competitor={};
                        toolbar.tab=0;
                    });
                };
            }],
            controllerAs:'competitorCtrl'
        };
    });
    aplicacionMundial.directive('competitorLogIn', function(){
        return{
            restrict:'E',
            templateUrl:'partials/competitor-login.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitor={};
                this.login=function(){
                    $http.post('URL del Servicio', JSON.stringify(self.competitor)).success(function(data){
                        self.competitor={};
                        toolbar.tab=0;
                    });
                };
            }],
            controllerAs:'competitorLogIn'
        };
    });
     aplicacionMundial.directive('competitorLogOut', function(){
        return{
            restrict:'E',
            templateUrl:'partials/competitor-logout.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                    $http.get('URL del Servicio').success(function(data){
                        self.competitors=data;
                    });
            }],
            controllerAs:'competitorLogOut'
        };
    });
})();



