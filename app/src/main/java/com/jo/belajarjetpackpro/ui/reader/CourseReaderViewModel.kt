package com.jo.belajarjetpackpro.ui.reader

import androidx.lifecycle.ViewModel
import com.jo.belajarjetpackpro.data.ContentEntity
import com.jo.belajarjetpackpro.data.ModuleEntity
import com.jo.belajarjetpackpro.utils.DataDummy

class CourseReaderViewModel : ViewModel() {
    var courseId = ""
    var moduleId = ""

    fun getModules(): ArrayList<ModuleEntity> = DataDummy.generateDummyModules(courseId)

    fun getSelectedModule(): ModuleEntity? {
        var module: ModuleEntity? = null
        for (i in 0 until getModules().size) {
            if (getModules()[i].mModuleId == moduleId) {
                module = getModules()[i]

                module.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + module.mTitle + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
                break
            }
        }
        return module
    }
}