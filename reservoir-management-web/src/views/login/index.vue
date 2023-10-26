<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on"
             label-position="left">

      <div class="title-container">
        <h1 class="title">管 理 员 登 陆</h1>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
        </span>
      </el-form-item>

      <el-button id="loginButton" :loading="loading" type="primary" style="width:100%;margin-bottom:10px;"
                 @click.native.prevent="handleLogin">登 陆
      </el-button>
      <el-button type="text" style="color: black" @click="checkTableVisible=true;checkForm.checkStr='';">注册管理员
      </el-button>
      <el-button type="text" style="color: black"
                 @click="retrieveTableVisible=true;retrieveForm={};retrieveUserForm={};inputAnswer='';">
        找回密码
      </el-button>

      <!-- 注册用户先进行校验 -->
      <el-dialog title="检验是否有权限注册管理员" :visible.sync="checkTableVisible" width="400px">
        <el-form label-position="left" :model="checkForm" status-icon :rules="checkRules" ref="ruleForm"
                 label-width="70px">
          <el-form-item label="检验码" prop="checkStr">
            <el-input v-model="checkForm.checkStr" style="color:black;">请输入检验码</el-input>
          </el-form-item>
          <el-button type="primary" @click="submitCheck">提交</el-button>
        </el-form>
      </el-dialog>

      <!-- 注册用户，填写用户信息 -->
      <el-dialog title="注册管理员用户" :visible.sync="userTableVisible" width="400px">
        <el-form id="userForm" :model="userForm" label-position="left" label-width="55px">
          <el-form-item label=" 用户名" prop="username">
            <el-input v-model="userForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密 码" prop="password">
            <el-input v-model="userForm.password" autocomplete="off" type="password"></el-input>
          </el-form-item>
          <p style="font-size: 15px">密保内容（用于找回密码）</p>
          <el-form-item label="问 题" prop="problem">
            <el-input v-model="userForm.problem" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答 案" prop="answer">
            <el-input v-model="userForm.answer" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="userTableVisible = false">取 消</el-button>
          <el-button type="primary" @click="addUser()">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 找回密码功能 -->
      <el-dialog title="所需找回的用户名" :visible.sync="retrieveTableVisible" width="400px">
        <el-form label-position="left" :model="retrieveForm" status-icon ref="ruleForm" label-width="70px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="retrieveForm.username" style="color:black;">请输入用户名</el-input>
          </el-form-item>
          <el-button type="primary" @click="submitRetrieve">确认用户名</el-button>
        </el-form>
      </el-dialog>

      <el-dialog title="回答密保问题" :visible.sync="inputAnswerTableVisible" width="400px">
        <p>问题：{{ retrieveUserForm.problem }}</p><br>
        <p>答案：</p>
        <el-input v-model="inputAnswer" placeholder="请输入答案" clearable style="background-color: #eff5f5"></el-input>
        <el-button type="primary" @click="checkAnswer" style="margin-top: 10px">确认答案</el-button>
      </el-dialog>


    </el-form>
  </div>
</template>

<script>
import {validUsername} from '@/utils/validate'
import fa from "element-ui/src/locale/lang/fa";
import {addUser, getUserInfoByUsername} from "@/api/user";

export default {
  name: 'Login',
  data() {
    //用户名和密码校验
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (!value) {
        // callback(new Error('密码不能小于3位'))
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    // 注册校验
    const check = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('校验信息不能为空'));
      } else if (value !== 'admin') {
        callback(new Error('请输入正确的校验值'));
      } else {
        callback();
      }
    }

    return {
      loginForm: {
        username: 'admin',
        password: 'admin'
      },
      loginRules: {
        username: [{required: true, trigger: 'blur', validator: validateUsername}],
        password: [{required: true, trigger: 'blur', validator: validatePassword}]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      // 实现注册账号的功能
      // 校验
      checkTableVisible: false,
      checkForm: {
        checkStr: '',
      },
      checkRules: {
        checkStr: [{required: true, trigger: 'blur', validator: check}]
      },
      // 添加管理员
      userForm: {
        username: '',
        password: '',
        problem: '',
        answer: ''
      },
      userTableVisible: false,
      formLabelWidth: '60px',

      // 实现找回密码的功能
      retrieveTableVisible: false,
      retrieveForm: {
        username: '',
      },
      retrieveUserForm: {
        username: '123',
        password: '',
        problem: '',
        answer: ''
      },
      inputAnswerTableVisible: false,
      inputAnswer: '',

    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({path: this.redirect || '/'})
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },

    // 添加用户之前先进行校验
    submitCheck() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          this.$message({
            message: '校验通过！',
            type: 'success'
          });
          this.checkTableVisible = false;
          this.userTableVisible = true;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    // 添加用户的点击事件
    addUser() {
      this.userTableVisible = false;
      // 调用请求方法
      addUser(this.userForm).then(response => {
        //成功提示
        this.$message({
          message: response.message,
          type: 'success'
        });
        this.userForm = {};
      })
    },

    // 找回密码
    submitRetrieve() {
      getUserInfoByUsername(this.retrieveForm.username).then(response => {
        const {data} = response;
        console.log(data.userInfo);
        const {username, password, problem, answer} = data.userInfo
        this.retrieveUserForm.username = username;
        this.retrieveUserForm.password = password;
        this.retrieveUserForm.problem = problem;
        this.retrieveUserForm.answer = answer;
        if (data) {
          this.retrieveTableVisible = false;
          this.inputAnswerTableVisible = true;
        }
      });
    },
    checkAnswer() {
      if (this.inputAnswer === this.retrieveUserForm.answer) {
        this.$alert('密码为：' + this.retrieveUserForm.password, '找回密码', {
          confirmButtonText: '确定',
        });
        this.inputAnswerTableVisible = false;
      } else {
        this.$message.error('回答错误！');
      }
    }

  }
}
</script>


<style>
.login-container .el-input input {
  color: black;
}
</style>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
      color: black;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>


<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  //添加背景图片
  background-image: url("../../assets/background.png");
  background-size: 100%;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

}

</style>
