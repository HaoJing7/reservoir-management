<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img src="@/assets/avatar.png" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
<!--          <router-link to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>-->
          <el-dropdown-item divided @click.native="handlePwd">
            <span style="display:block;">修改密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">注销</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogFormVisible"
      width="568px"
      class="pwdCon"
      @close="handlePwdClose()"
    >
      <el-form :model="passwordForm" label-width="85px" :rules="rules" ref="form">
        <el-form-item label="原密码：" prop="oldPassword">
          <el-input
            v-model="passwordForm.oldPassword"
            type="password"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码：" prop="newPassword">
          <el-input
            v-model="passwordForm.newPassword"
            type="password"
            placeholder="6 - 20位密码，数字或字母，区分大小写"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认：" prop="affirmPassword">
          <el-input
            v-model="passwordForm.affirmPassword"
            type="password"
            placeholder="请再输入一遍新密码"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handlePwdClose()">取 消</el-button>
        <el-button type="primary" @click="handleSave()">保 存</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import {updatePwd} from "@/api/user";

export default {
  data() {
    const validatePwd = (rule, value, callback) => {
      const reg = /^[0-9A-Za-z]{6,20}$/
      if (!value) {
        callback(new Error('请输入'))
      } else if (!reg.test(value)) {
        callback(new Error('6 - 20位密码，数字或字母，区分大小写'))
      } else {
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.passwordForm.newPassword) {
        callback(new Error('密码不一致，请重新输入密码'))
      } else {
        callback()
      }
    }
    return {
      passwordForm: {
        oldPassword: '',
        newPassword: '',
        affirmPassword: '',
      },
      rules: {
        oldPassword: [{required: true, validator: validatePwd, trigger: 'blur'}],
        newPassword: [{required: true, validator: validatePwd, trigger: 'blur'}],
        affirmPassword: [{required: true, validator: validatePass2, trigger: 'blur'}],
      },
      dialogFormVisible: false,
    }
  },
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    handlePwd() {
      this.dialogFormVisible = true;
      console.log("修改密码");
    },
    handlePwdClose() {
      this.passwordForm.oldPassword = '';
      this.passwordForm.newPassword = '';
      this.passwordForm.affirmPassword = '';
      this.dialogFormVisible = false;
    },
    handleSave(){
      this.$refs.form.validate (valid => {
        if (valid) {
          console.log("修改密码");
          updatePwd(this.passwordForm.newPassword).then(response => {
            this.handlePwdClose();
          })
        } else {
          console.log('error submit!!');
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
.navbar {
  .pwdCon {
    .el-dialog__body {
      padding: 60px 100px 0;
    }
    .el-input__inner {
      padding: 0 12px;
    }
    .el-form-item {
      margin-bottom: 26px;
    }
    .el-form-item__label {
      text-align: left;
    }
    .el-dialog__footer {
      padding-top: 14px;
    }
  }
}
</style>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
