(function(n,m,b,d,h,e,a,g,l){b(m).ready(function(){function
p(b){"change_view"!=tribe_events_bar_action&&(b.preventDefault(),a.ajax_running||(a.paged=1,a.view="list",a.popping=!1,e.pre_ajax(function(){k()})))}function
k(){a.ajax_running=!0;if(!a.popping){a.filter_cats&&(d.cur_url=b("#tribe-events-header").data("baseurl"));var
c=b("#tribe-events-list-hash").val();a.params={action:"tribe_list",tribe_paged:a.paged,tribe_event_display:a.view};a.url_params={action:"tribe_list",tribe_paged:a.paged,tribe_event_display:a.view};
c.length&&(a.params.hash=c);a.category&&(a.params.tribe_event_category=a.category);b(h).trigger("tribe_ev_serializeBar");if(e.invalid_date_in_params(a.params)){a.ajax_running=!1;return}b("#tribe-events-content
.tribe-events-loop").tribe_spin();a.params=b.param(a.params);a.url_params=b.param(a.url_params);b(h).trigger("tribe_ev_collectParams");a.pushstate=!1;a.do_string=!0}g.pushstate&&!a.filter_cats?(l&&debug.time("List
View Ajax
Timer"),b(h).trigger("tribe_ev_ajaxStart").trigger("tribe_ev_listView_AjaxStart"),
b.post(TribeList.ajaxurl,a.params,function(c){a.initial_load=!1;e.enable_inputs("#tribe_events_filters_form","input,
select");if(c.success){a.ajax_running=!1;d.ajax_response={total_count:parseInt(c.total_count),view:c.view,max_pages:c.max_pages,tribe_paged:c.tribe_paged,timestamp:(new
Date).getTime()};b("#tribe-events-list-hash").val(c.hash);var
f="",f=b.isFunction(b.fn.parseHTML)?b.parseHTML(c.html):c.html;b("#tribe-events-content").replaceWith(f);0===c.total_count&&b("#tribe-events-header
.tribe-events-sub-nav").empty();
a.page_title=b("#tribe-events-header").data("title");m.title=a.page_title;a.do_string&&history.pushState({tribe_params:a.params,tribe_url_params:a.url_params},a.page_title,d.cur_url+"?"+a.url_params);a.pushstate&&history.pushState({tribe_params:a.params,tribe_url_params:a.url_params},a.page_title,d.cur_url);b(h).trigger("tribe_ev_ajaxSuccess").trigger("tribe_ev_listView_AjaxSuccess");l&&debug.timeEnd("List
View Ajax
Timer")}})):n.location=a.url_params.length?d.cur_url+"?"+a.url_params:d.cur_url}var
f= e.get_url_param("tribe_paged"),q=b("#tribe-events >
.tribe-events-venue");f&&(a.paged=f);g.pushstate&&!g.map_view()&&(f="action=tribe_list&tribe_paged="+a.paged,d.params.length&&(f=f+"&"+d.params),a.category&&(f=f+"&tribe_event_category="+a.category),history.replaceState({tribe_params:f,tribe_url_params:d.params},m.title,location.href),b(n).on("popstate",function(b){if((b=b.originalEvent.state)&&!q.length)a.do_string=!1,a.pushstate=!1,a.popping=!0,a.params=b.tribe_params,a.url_params=b.tribe_url_params,
e.pre_ajax(function(){k()}),e.set_form(a.params)}));b("#tribe-events-content-wrapper").on("click","li.tribe-events-nav-next
a",function(c){c.preventDefault();a.ajax_running||(b(this).parent().is(".tribe-events-past")?a.view="past":a.view="list",d.cur_url=e.url_path(b(this).attr("href")),a.paged++,a.popping=!1,e.pre_ajax(function(){k()}))}).on("click","li.tribe-events-nav-previous
a",function(c){c.preventDefault();a.ajax_running||(b(this).parent().is(".tribe-events-past")?a.view="past":a.view="list",
d.cur_url=e.url_path(b(this).attr("href")),1
<a.paged &&a.paged--,a.popping=!1,e.pre_ajax(function(){k()}))});e.snap(
	"#tribe-events-content-wrapper","#tribe-events-content-wrapper","#tribe-events-footer .tribe-events-nav-previous
	a, #tribe-events-footer .tribe-events-nav-next
	a");if(g.no_bar()||g.live_ajax()&&g.pushstate)b("#tribe-events-bar").on("changeDate","#tribe-bar-date",function(b){g.reset_on()||(a.popping=!1,p(b))});b(
	"form#tribe-bar-form").on("submit",function(b){a.popping=!1;p(b)});b(h).on(
	"tribe_ev_runAjax",
function(){k()});l&&debug.info("TEC Debug:
	tribe-events-ajax-list.js successfully
	loaded");a.view&&l&&debug.timeEnd("Tribe JS InitTimer")})})(window,document,jQuery,tribe_ev.data,tribe_ev.events,tribe_ev.fn,tribe_ev.state,tribe_ev.tests,tribe_debug);
