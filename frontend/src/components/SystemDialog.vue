<template>
	<el-dialog
		v-model="dialogVisible"
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
        <!-- @R: better use variable instead of fixed text for button label -->
				<el-button type="danger" @click="onClose">{{ t("common.cancel") }}</el-button>
				<el-button type="primary" @click="onConfirm">
					{{ t("common.confirm") }}
				</el-button>
			</div>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { useI18n } from "vue-i18n";

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

const emit = defineEmits(["update:visible", "onClose", "onConfirm"]);

const dialogVisible = computed({
	get: () => props.visible,
	set: (val) => emit("update:visible", val),
});

const onClose = (done?: () => void) => {
	dialogVisible.value = false;
	emit("onClose");
	if (done) done();
};
const onConfirm = () => {
	emit("onConfirm");
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


