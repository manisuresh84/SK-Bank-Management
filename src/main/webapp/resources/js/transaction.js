angular
		.module('transview', [])
		.controller(
				'ViewTransaction',
				function($scope, $http) {
					$http
							.get(
									'http://localhost:8080/bankmanagement/api/customtransactions/1234567890123457')
							.then(function(response) {
								$scope.transdata = response.data;
								console.log(JSON.stringify(response.data));
							});
				});
