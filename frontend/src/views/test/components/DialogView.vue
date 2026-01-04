<template>
  <h3>customed dialog</h3>
	<el-button plain @click="openDialog">
		Click to open the Dialog
	</el-button>
  <SystemDialog
    :title="dialog.title"
    :visible="dialog.visible"
    :width="dialog.width"
    :height="dialog.height"
    @on-close="onClose"
    @on-confirm="onConfirm"
  >
    <template v-slot:content>
      <span>弹窗内容放这里</span>
    </template>
  </SystemDialog>
</template>
<script lang="ts" setup>
  import SystemDialog from '@/components/SystemDialog.vue';
  import { useDialog } from '@/hooks/useDialog'

  const { dialog, onShow, onClose, onConfirm} = useDialog()
  const openDialog = () => {
    dialog.title = 'Test Dialog Title'
    onShow()
  }
</script>
