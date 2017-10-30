package tech.wetech.admin.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tech.wetech.admin.model.system.Resource;
import tech.wetech.admin.service.system.ResourceService;
import tech.wetech.admin.web.controller.base.BaseController;
import tech.wetech.admin.web.dto.JsonResult;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute("types")
    public Resource.ResourceType[] resourceTypes() {
        return Resource.ResourceType.values();
    }

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toPage(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "system/resource";
    }

    @ResponseBody
    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(Resource resource) {
        resourceService.createResource(resource);
        return this.renderSuccess();
    }

    @ResponseBody
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(Resource resource) {
        resourceService.updateResource(resource);
        return this.renderSuccess();
    }

    @ResponseBody
    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public JsonResult delete(@PathVariable("id") Long id) {
        resourceService.deleteResource(id);
        return this.renderSuccess();
    }

}