<template>
	<el-dialog
		v-model="props.visible"
		:title="props.title"
		:width="props.width + 'px'"
		:before-close="onClose"
    append-to-body
	>
    <div :style="{height:props.height + 'px'}">
      <slot name="content"></slot>
    </div>
		<template #footer>
			<div class="dialog-footer">
				<el-button type="danger" @click="onClose">{{ t("common.cancel") }}</el-button>
				<el-button type="primary" @click="onConfirm">
					{{ t("common.confirm") }}
				</el-button>
			</div>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useI18n } from "vue-i18n";
import { ElMessageBox } from "element-plus";

export interface DialogProps {
	title?: string;
	visible?: boolean;
	width?: string | number;
	height?: string | number;
}

const props = withDefaults(defineProps<DialogProps>(), {
	title: '',
	visible: false,
	width: 630,
	height: 280,
});

const { t } = useI18n();

// emit events
const emit = defineEmits(["onClose","onConfirm"])
const onClose = () => {
  emit("onClose");
};
const onConfirm = () => {
  emit("onConfirm");
};

const dialogVisible = ref(false);

const handleClose = (done: () => void) => {
	ElMessageBox.confirm("Are you sure to close this dialog?")
		.then(() => {
			done();
		})
		.catch(() => {
			// catch error
		});
};
</script>

<style lang="scss" scope>
.container {
  overflow-x: initial;
  overflow-y: auto;
}

.el-dialog {
  padding: 0px;
  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;
  .el-dialog__header {
    padding: 10px;
    margin-right: 0px;
    border-top-left-radius: 7px !important;
    border-top-right-radius: 7px !important;
    background-color: #00b894 !important;
    .el-dialog__title {
      color: #fff;
      font-size: 16px;
      font-weight: 600;
    }
  }
  .el-dialog__headerbtn {
    .el-dialog__close {
      color: #fff;
    }
  }
  .el-dialog__body {
    padding: 10px;
  }
  .el-dialog__footer {
    border-top: 1px solid #e8eaec !important;
    padding: 10px;
  }
}
</style>