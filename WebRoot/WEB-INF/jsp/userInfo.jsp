<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>

<!doctype html>
<html class="no-js">
<head>
	<base href="<%=basePath%>"></base>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>用户详情</title>
	<meta name="description" content="这是一个 user 页面">
	<meta name="keywords" content="user">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="icon" type="image/png" href="assets/i/favicon.png">
	<link rel="apple-touch-icon-precomposed" href="assets/i/favicon.png">
	<meta name="apple-mobile-web-app-title" content="Amaze UI" />
	<link rel="stylesheet" href="assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="assets/css/admin.css">

	<style type="text/css">
		#edit-div {
			float: right;
			width: 20%;
			margin-right: 25%;
		}
		#save-div {
			float: right;
			width: 20%;	
			margin-left: 100px;
		}
		.am-form-group {
			height: 50px;
		}
		.my-label {
			float:left;
			margin-left: 20px;
		}
		.my-radio {
			float:left;
			margin-right: 20px; 
		}
		.intro-div {
			height: 100px;
		}
		.mybtndiv {
			float: left;
			width: 25%;
			margin-right: 20px;
		}
		.mybtndiv > button{
			width: 100%;
		}
	</style>

</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal Information</small></div>
			</div>

			<hr/>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8" id="right-div">
					<div class="am-panel am-panel-default">
						<div class="am-panel-bd">
							<div class="am-g">
							</div>
						</div>
					</div>

					<div class="am-panel am-panel-default">
						<div class="am-panel-bd">
							<div class="user-info">
								<p>等级信息</p>
								<div class="am-progress am-progress-sm">
									<div class="am-progress-bar" v-bind:style="{width: percent+'%'}"></div>
								</div>
								<p class="user-info-order">
									当前等级:&nbsp;<strong>LV{{level}}</strong> &nbsp;&nbsp;&nbsp;
									已获经验:&nbsp;<strong>{{exp}}</strong>&nbsp;&nbsp;&nbsp;
									距离下一级别:&nbsp;<strong>{{requiredExp}}</strong>
								</p>
							</div>
						</div>
					</div>

					<div class="am-panel am-panel-default">
						<div class="am-panel-bd">
							<div class="mybtndiv" v-if="isEditable">
								<button onclick="window.location.href='user/getUserOperateRecord'" 
										class="am-btn am-btn-primary">
									操作记录
								</button>
							</div>
							<div class="mybtndiv">
								<button onclick="window.location.href='tiezi/getAllTieziByUserId?userId=${userDetail.userid}'" 
										class="am-btn am-btn-primary">
									全部发帖
								</button>
							</div>
							<div class="mybtndiv">
								<button onclick="window.location.href='tiezi/getTieziReplyByUserId?userId=${userDetail.userid}'" 
										class="am-btn am-btn-primary">
									全部回帖
								</button>
							</div>
							<div style="clear:both"></div>
						</div>
					</div>

				</div>

				<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4" id="form-group">
					<form class="am-form am-form-horizontal" action="user/updateUserDetail" method="post">
						<div class="am-form-group">
							<label for="username" class="am-u-sm-3 am-form-label">昵称 / Name</label>
							<div class="am-u-sm-9" v-if="edit" >
								<input type="text" id="username" placeholder="姓名 / Name" v-model="username" disabled="disabled">
							</div>
							<div class="am-form-label my-label" v-else>
								{{username}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="sign" class="am-u-sm-3 am-form-label">签名 / Sign</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="sign" placeholder="输入你的签名 / Sign"  v-model="sign" name="sign">
							</div>
							<div class="am-form-label my-label" v-else>
								{{sign}}
							</div>
						</div>	

						<div class="am-form-group">
							<label for="phoneNum" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="tel" id="phoneNum" placeholder="输入你的电话 / Phone"  v-model="phoneNum" name="phoneNum">
							</div>
							<div class="am-form-label my-label" v-else>
								{{phoneNum}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="email" id="email" placeholder="输入你的电子邮件 / Email" v-model="email" name="email">
							</div>
							<div class="am-form-label my-label" v-else>
								{{email}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="gender" class="am-u-sm-3 am-form-label">性别 / Gender</label>
							<div class="am-u-sm-9" v-if="edit" id="gender">
								<div class="am-radio my-radio"><label><input type="radio" v-model="gender" value="男" checked="checked" name="gender"> 男</label></div>
								<div class="am-radio my-radio"><label><input type="radio" v-model="gender" value="女" checked="checked" name="gender"> 女</label></div>
								<div class="am-radio my-radio"><label><input type="radio" v-model="gender" value="未定" checked="checked" name="gender"> 未定</label></div>
							</div>
							<div class="am-form-label my-label" v-else>
								{{gender}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="age" class="am-u-sm-3 am-form-label">年龄 / Age</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="age" disabled="disabled"  v-model="age">
							</div>
							<div class="am-form-label my-label" v-else>
								{{age}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="birthday" class="am-u-sm-3 am-form-label">生日 / Birthday</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="birthday" placeholder="输入你的生日 / Birthday  (yyyy-mm-dd)"  v-model="birthday" name="birthday">
							</div>
							<div class="am-form-label my-label" v-else>
								{{birthday}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="bloodType" class="am-u-sm-3 am-form-label">血型 / Blood Type</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="bloodType" placeholder="输入你的血型 / Blood Type"  v-model="bloodType" name="bloodType">
							</div>
							<div class="am-form-label my-label" v-else>
								{{bloodType}}
							</div>
						</div>
						
						<div class="am-form-group">
							<label for="occupation" class="am-u-sm-3 am-form-label">职业 / Occupation</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="occupation" placeholder="输入你的职业 / Occupation"  v-model="occupation" name="occupation">
							</div>
							<div class="am-form-label my-label" v-else>
								{{occupation}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="company" class="am-u-sm-3 am-form-label">公司 / Company</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="company" placeholder="输入你的公司birthLand / Company"  v-model="company" name="company">
							</div>
							<div class="am-form-label my-label" v-else>
								{{company}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="birthLand" class="am-u-sm-3 am-form-label">出生地 / Birth Land</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="birthLand" placeholder="输入你的出生地 / Birth Land"  v-model="birthLand" name="birthLand">
							</div>
							<div class="am-form-label my-label" v-else>
								{{birthLand}}
							</div>
						</div>

						<div class="am-form-group">
							<label for="liveLand" class="am-u-sm-3 am-form-label">居住地 / Live Land</label>
							<div class="am-u-sm-9" v-if="edit">
								<input type="text" id="liveLand" placeholder="输入你的居住地 / Live Land"  v-model="liveLand" name="liveLand">
							</div>
							<div class="am-form-label my-label" v-else>
								{{liveLand}}
							</div>
						</div>

						<div class="am-form-group intro-div">
							<label for="intro" class="am-u-sm-3 am-form-label">简介 / Introduction</label>
							<div class="am-u-sm-9" v-if="edit">
								<textarea rows="4" placeholder="简介 / Introduction" style="resize:none" v-model="intro" name="intro" ></textarea>
							</div>
							<div class="am-form-label my-label" v-else>
								{{intro}}
							</div>
						</div>

						<div class="am-form-group" height="100px" v-if="isEditable">
							<div class="am-u-sm-3 am-u-sm-push-3" v-if="edit">
								<button type="submit" class="am-btn am-btn-primary" id="save-button">保存修改</button>
							</div>
							<div class="am-u-sm-3 am-u-sm-push-3" id="edit-div">
								<button type="button" class="am-btn am-btn-primary" @click="changeEditStatus">{{editButton}}</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
	<!-- content end -->
</div>


<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/vue.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">
	var rightDiv = new Vue({
		el: "#right-div",
		data: {
			level: ${userDetail.userLevel},
			exp: ${userDetail.exp},
			isEditable: ${isEditable}
		},
		computed: {
			requiredExp: function() {
				var current = this.exp;
				for (var i = 1; i < this.level; i++) {
					current -= i*100;
				}
				return this.level*100 - current; 
			},
			percent: function() {
				var current = this.exp;
				for (var i = 1; i < this.level; i++) {
					current -= i*100;
				}
				return current / this.level;
			}
		}

	})

	var formGroup = new Vue({
		el: "#form-group",
		data: {
			edit: false,
			username: "${userDetail.username}",
			sign: "${userDetail.sign}",
			phoneNum: "${userDetail.phoneNum}",
			email: "${userDetail.email}",
			gender: "${userDetail.gender}",
			age: "${userDetail.age}",
			birthday: "${userDetail.birthday}".substring(0,10),
			bloodType: "${userDetail.bloodType}",
			occupation: "${userDetail.occupation}",
			company: "${userDetail.company}",
			birthLand: "${userDetail.birthLand}",
			liveLand: "${userDetail.liveLand}",
			intro: "${userDetail.intro}",
			editButton: "编辑信息",
			isEditable: ${isEditable}
		},
		methods: {
			changeEditStatus: function() {
				this.edit = !this.edit;
				if(this.edit){
					this.editButton = "取消编辑"
				}else{
					this.editButton = "编辑信息"
				}
			}
		}
	})
</script>
</body>
</html>
